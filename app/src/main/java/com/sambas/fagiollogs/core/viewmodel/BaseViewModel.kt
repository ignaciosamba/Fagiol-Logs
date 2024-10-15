package com.sambas.fagiollogs.core.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sambas.fagiollogs.core.design.BaseUiState
import com.sambas.fagiollogs.core.design.loader.ScreenLoadingType
import com.sambas.fagiollogs.core.design.scaffold.LoadingModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.net.UnknownHostException

interface UiEvent

/**
 * Base view model, to be inherit in all the viewmodels and manage the emits/set the states and events.
 *
 * @property [savedStateHandle] use to save the state in case of a process death
 * @param [initialState] [BaseUiState] class with the state information at the first moment.
 * @property [stateKey] String used as default or state name key for the get/set state on the [savedStateHandle]
 * @property [useLoadingState] Boolean use to disable or not use the loading feature.
 */
abstract class BaseViewModel<S : BaseUiState, E : UiEvent>(
    private val savedStateHandle: SavedStateHandle,
    initialState: S,
    private val stateKey: String = "viewModelState",
    private val useLoadingState: Boolean = true,
    private val loadingStateUpdater: (S, ScreenLoadingType) -> S = { newState, _ -> newState },
) : ViewModel() {
    private val _state = MutableStateFlow<S?>(savedStateHandle[stateKey] ?: initialState)
    val state: StateFlow<S> = _state.filterNotNull().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = initialState
    )

    /**
     * The default [ScreenLoadingType] when executing actions.
     */
    open val defaultLoadingType: ScreenLoadingType = ScreenLoadingType.VisibleAndCancellable

    private val _events = Channel<E>()
    val events = _events.receiveAsFlow()

    protected fun setState(reduce: (S) -> S) {
        val currentState = _state.value
        if (currentState != null) {
            val newState = reduce(currentState)
            _state.update { newState }
        }
    }

    protected fun <T> emitState(stateField: (S) -> T): StateFlow<T> {
        return state.map { stateField(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = stateField(state.value)
            )
    }

    fun emitEvent(event: E) {
        viewModelScope.launch {
            _events.send(event)
        }
    }

    private fun updateLoading(newLoadingType: ScreenLoadingType) {
        setState { currentState ->
            loadingStateUpdater(currentState, newLoadingType)
        }
    }

    protected fun <T> launchNetworkCall(
        action: suspend () -> T,
        onSuccess: (T) -> Unit,
        onError: (Throwable) -> Unit = { },
        onNoConnection: () -> Unit = { },
    ) {
        viewModelScope.launch {
            if (state.value.loadingModel != LoadingModel.disable) {
                updateLoading(ScreenLoadingType.Visible)
            }
            try {
                val result = action()
                onSuccess(result)
            } catch (e: UnknownHostException) {
                onNoConnection()
            } catch (e: Exception) {
                onError(e)
            } finally {
                if (state.value.loadingModel != LoadingModel.disable) {
                    updateLoading(ScreenLoadingType.None)
                }
            }
        }
    }
}