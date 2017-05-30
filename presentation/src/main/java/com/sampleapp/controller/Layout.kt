package com.sampleapp.controller

import android.support.annotation.LayoutRes


@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS, AnnotationTarget.FILE)
annotation class Layout(@LayoutRes val value: Int)