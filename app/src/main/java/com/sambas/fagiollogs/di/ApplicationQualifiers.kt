package com.sambas.fagiollogs.di

import javax.inject.Qualifier

// Define a custom scope annotation
@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ApplicationScope