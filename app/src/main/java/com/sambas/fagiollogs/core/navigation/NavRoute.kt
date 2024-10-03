package com.sambas.fagiollogs.core.navigation

import android.net.Uri
import kotlin.reflect.KClass

/**
 * The route of a [NavigationNode].
 */
@JvmInline
public value class NavRoute private constructor(internal val value: String) {
    public companion object {
        internal operator fun invoke(value: String, encoded: Boolean = false): NavRoute {
            return NavRoute(if (encoded) value else Uri.encode(value))
        }

        internal operator fun invoke(kClass: KClass<*>): NavRoute {
            return NavRoute(kClass.qualifiedName!!)
        }
    }
}