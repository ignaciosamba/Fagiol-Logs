package com.sambas.fagiollogs.core.navigation

import android.net.Uri

/**
 * A [NavUri] builder that allows to safely format URIs.
 *
 * For example `archive/2022%2F12%2F01/entries?filter=2` can be built with:
 * ```
 * NavigationRouteBuilder("archive")
 *     .addPathSegment("2022/12/01")
 *     .addPathSegment("entries")
 *     .addQueryParameter("filter", "2")
 *     .build()
 * ```
 */
public class NavUriBuilder(basePath: String) {
    private val pathBuilder = StringBuilder(basePath)
    private val queryBuilder = StringBuilder()

    /**
     * Append a segment to the current path.
     *
     * @param segment The segment to append to the path.
     * @param encoded Whether [segment] is percent encoded.
     */
    public fun addPathSegment(segment: String, encoded: Boolean = false): NavUriBuilder {
        if (!pathBuilder.endsWith("/")) {
            pathBuilder.append("/")
        }

        pathBuilder.append(if (encoded) segment else Uri.encode(segment))
        return this
    }

    /**
     * Add a query parameter.
     *
     * @param name The name of the query parameter.
     * @param value The value of the query parameter.
     * @param encoded Whether [value] is percent encoded.
     */
    public fun addQueryParameter(
        name: String,
        value: String,
        encoded: Boolean = false
    ): NavUriBuilder {
        if (queryBuilder.isEmpty()) {
            queryBuilder.append("?")
        } else {
            queryBuilder.append("&")
        }
        queryBuilder.append(name)
        queryBuilder.append("=")
        queryBuilder.append(if (encoded) value else Uri.encode(value))
        return this
    }

    /**
     * Build the [NavUri].
     */
    public fun build(): NavUri = NavUri(pathBuilder.toString() + queryBuilder.toString())
}