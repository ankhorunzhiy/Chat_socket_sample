package com.newssample.di.components

import android.content.Context
import com.android.newssamle.domain.data.executor.PostExecutionThread
import com.android.newssamle.domain.data.executor.ThreadExecutor
import com.google.gson.Gson
import com.newssample.Application
import com.newssample.di.module.ApplicationModule
import com.newssample.di.module.NetworkModule
import com.newssample.util.ApplicationScope

import dagger.Component

@ApplicationScope
@Component(modules = arrayOf(ApplicationModule::class, NetworkModule::class))
interface ApplicationComponent {
    fun inject(application: Application)
    fun threadExecutor(): ThreadExecutor
    fun postExecutionThread(): PostExecutionThread
    fun gson(): Gson
    fun context(): Context
}
