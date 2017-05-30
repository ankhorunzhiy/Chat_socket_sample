package com.sampleapp.di

import javax.inject.Scope
import kotlin.reflect.KClass

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ScreenScope(val value: KClass<*>)