package com.newssample.util

import android.support.annotation.LayoutRes

import javax.inject.Scope

/**
 * Created by Tony on 09.05.17.
 */

annotation class Layout(@LayoutRes val value: Int)

@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ActivityScope
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ControllerScope
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ApplicationScope
