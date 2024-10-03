package com.example.pokemonbox.core.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.net.UnknownHostException

interface UiEvent

/**BaseUiState interface to use with every State (apply only for the one that are applied to VMs),
* in order to set automatically the loading when launchNetworkCall is call.
 *
 * @property [isLoading] Boolean to set the loading in the state.
**/
interface BaseUiState {
    val isLoading: Boolean
}

/**
 * Base view model, to be inherit in all the viewmodels and manage the emits/set the states and events.
 *
 * @property [savedStateHandle] use to save the state in case of a process death
 * @param [initialState] [BaseUiState] class with the state information at the first moment.
 * @property [stateKey] String used as default or state name key for the get/set state on the [savedStateHandle]
 * @property [useLoadingState] Boolean use to disable or not use the loading feature.
 * @property [setLoadingState] Lambda to pass the loading Boolean from the state, and automatically set when
 * the launchNetworkCall is running.
 */
abstract class BaseViewModel<S : BaseUiState, E : UiEvent>(
    private val savedStateHandle: SavedStateHandle,
    initialState: S,
    private val stateKey: String = "viewModelState",
    private val useLoadingState: Boolean = true,
    private val setLoadingState: (S, Boolean) -> S
) : ViewModel() {
    private val _state = MutableStateFlow<S?>(savedStateHandle[stateKey] ?: initialState)
    val state: StateFlow<S> = _state.filterNotNull().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = initialState
    )

    private val _events = Channel<E>()
    val events = _events.receiveAsFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    protected fun setState(reduce: (S) -> S) {
        val currentState = _state.value
        if (currentState != null) {
            val newState = reduce(currentState)
            _state.update { newState }
            savedStateHandle[stateKey] = newState
        }
    }

    protected fun <T> emitState(stateField: (S) -> T): StateFlow<T>? {
        return state.map { stateField(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = stateField(state.value)
            )
    }

    protected fun emitEvent(event: E) {
        viewModelScope.launch {
            _events.send(event)
        }
    }

    private fun setLoading(loading: Boolean) {
        setState { currentState ->
            setLoadingState(currentState, loading)
        }
    }

    protected fun <T> launchNetworkCall(
        action: suspend () -> T,
        onSuccess: (T) -> Unit,
        onError: (Throwable) -> Unit = { },
        onNoConnection: () -> Unit = { },
        useLoadingState: Boolean = this.useLoadingState
    ) {
        viewModelScope.launch {
            if (useLoadingState) setLoading(true)
            try {
                val result = action()
                onSuccess(result)
            } catch (e: UnknownHostException) {
                onNoConnection()
            } catch (e: Exception) {
                onError(e)
            } finally {
                if (useLoadingState) setLoading(false)
            }
        }
    }
}