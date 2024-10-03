package com.sambas.fagiollogs.core.navigation

import android.net.Uri

/**
 * A [NavRoute] builder that allows to safely format URIs with argument placeholders.
 *
 * For example `some/{uri}/with?placeholders={placeholders}` can be built with:
 * ```
 * NavigationRouteBuilder("some")
 *     .addPathSegmentPlaceholder("uri")
 *     .addPathSegment("with")
 *     .addQueryParameterPlaceholder("placeholders")
 *     .build()
 * ```
 */
public class NavRouteBuilder(basePath: String) {
    private val pathBuilder = StringBuilder(basePath)
    private val queryBuilder = StringBuilder()

    /**
     * Add a segment to the current path.
     */
    public fun addPathSegment(segment: String, encoded: Boolean = false): NavRouteBuilder {
        if (!pathBuilder.endsWith("/")) {
            pathBuilder.append("/")
        }
        pathBuilder.append(if (encoded) segment else Uri.encode(segment))
        return this
    }

    /**
     * Add a segment placeholder for a mandatory route argument.
     */
    public fun addPathSegmentPlaceholder(placeholder: String): NavRouteBuilder {
        if (!pathBuilder.endsWith("/")) {
            pathBuilder.append("/")
        }
        pathBuilder.append("{").append(placeholder).append("}")
        return this
    }

    /**
     * Add a query parameter placeholder for an optional route argument.
     */
    public fun addQueryParameterPlaceholder(placeholder: String): NavRouteBuilder {
        if (queryBuilder.isEmpty()) {
            queryBuilder.append("?")
        } else {
            queryBuilder.append("&")
        }
        queryBuilder.append(placeholder)
        queryBuilder.append("=")
        queryBuilder.append("{").append(placeholder).append("}")
        return this
    }

    /**
     * Build the [NavigationRoute].
     */
    public fun build(): NavRoute = NavRoute(
        value = pathBuilder.toString() + queryBuilder.toString(),
        encoded = true,
    )
}