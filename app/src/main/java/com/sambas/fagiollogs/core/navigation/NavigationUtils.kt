package com.sambas.fagiollogs.core.navigation

/**
 * Generate a unique and stable identifier for this [NavigationNode].
 */
internal fun NavigationNodeNew.generateRoute(): NavRoute =
    NavRoute(this::class)

/**
 * Find all the `objects` defined inside of `Type` that implement `Type`.
 *
 * This is a way to find all the destinations of a [NavigationGraph] automatically using reflection,
 * at least until https://youtrack.jetbrains.com/issue/KT-25871 is fixed.
 */
public inline fun <Graph : BaseNavGraph<Type>, reified Type : NavigationNodeNew> Graph.findDestinations(
    type: Class<Type> = Type::class.java,
): List<Type> {
    return type.classes.mapNotNull { nestedClass ->
        if (type.isAssignableFrom(nestedClass)) {
            runCatching {
                nestedClass.getField("INSTANCE").get(null) as Type
            }.getOrElse {
                error("$nestedClass has no INSTANCE field of type ${type.name}, is it a kotlin object?")
            }
        } else {
            null
        }
    }
}

/**
 * Return true if this node has the given string as route.
 */
public fun NavigationNodeNew.hasRoute(route: String?): Boolean = this.route.value == route