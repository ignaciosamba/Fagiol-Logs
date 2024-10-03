package com.sambas.fagiollogs.core.navigation

import android.os.Parcelable
import androidx.lifecycle.SavedStateHandle


/**
 * Class used to pass arguments safely in Compose navigation.
 * Accepts Parcelable types, primitive types, and their boxed equivalents.
 */
class NavArg<T : Any?>(private val key: String) {
    @Suppress("UNCHECKED_CAST")
    fun get(savedStateHandle: SavedStateHandle): T? {
        return when (val value = savedStateHandle.get<Any?>(key)) {
            is Parcelable, is String, is Number, is Boolean, null -> value as T?
            else -> throw IllegalArgumentException("Unsupported type for key $key")
        }
    }

    fun set(savedStateHandle: SavedStateHandle, value: T) {
        when (value) {
            is Parcelable, is String, is Number, is Boolean, null -> savedStateHandle[key] = value
            else -> throw IllegalArgumentException("Unsupported type: ${value?.javaClass?.simpleName}")
        }
    }
}