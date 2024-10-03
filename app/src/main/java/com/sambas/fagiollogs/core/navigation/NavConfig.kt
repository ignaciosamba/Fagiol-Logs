package com.sambas.fagiollogs.core.navigation

import android.os.Parcelable

public object NavConfig {
    //TODO: We need to implement the logger with Timber (we're using Log.x() for now).
//    public var logger: NavigationLogger? = null
    public var argumentsDebugger: ((Parcelable) -> Unit)? = null
}