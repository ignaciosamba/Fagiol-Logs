package com.sambas.fagiollogs.domain.utils

fun extractPasswordRequirementsFromFirebase(errorText: String?): String? {
    val regex = "\\[.*?\\[(.+?)\\].*?\\]".toRegex(RegexOption.DOT_MATCHES_ALL)
    return errorText?.let { regex.find(it)?.groupValues?.get(1)?.substringBefore(",") }
}