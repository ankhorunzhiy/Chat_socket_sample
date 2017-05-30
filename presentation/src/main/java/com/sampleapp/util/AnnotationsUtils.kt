package com.sampleapp.util

import android.support.annotation.LayoutRes


import javax.inject.Scope
import kotlin.reflect.KClass

/**
 * Created by Tony on 09.05.17.
 */

annotation class Layout(@LayoutRes val value: Int)

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ScreenScope(val value: KClass<*>)
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ApplicationScope
