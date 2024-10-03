package com.sambas.fagiollogs.core.navigation

import android.os.Parcelable

/**
 * A route for reaching a [NavigationDestinationWithArgs].
 */
public class NavUriWithArgs<ArgType : Parcelable> internal constructor(
    internal val uri: String,
    public val args: ArgType,
    internal val argsKey: String,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as NavUriWithArgs<*>

        if (uri != other.uri) return false
        if (args != other.args) return false
        if (argsKey != other.argsKey) return false

        return true
    }

    override fun hashCode(): Int {
        var result = uri.hashCode()
        result = 31 * result + args.hashCode()
        result = 31 * result + argsKey.hashCode()
        return result
    }

    override fun toString(): String {
        return "NavigationUriWithArgs(uri='$uri', args=$args, argsKey='$argsKey')"
    }
}