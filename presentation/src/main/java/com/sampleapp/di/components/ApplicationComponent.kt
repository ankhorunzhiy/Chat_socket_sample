package com.sampleapp.di.components

import android.content.Context
import com.sampleapp.domain.data.executor.PostExecutionThread
import com.sampleapp.domain.data.executor.ThreadExecutor
import com.google.gson.Gson
import com.sampleapp.Application
import com.sampleapp.di.ApplicationScope
import com.sampleapp.di.module.ApplicationModule
import com.sampleapp.domain.data.repository.DataRepositoryInterface

import dagger.Component

@ApplicationScope
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {
    fun inject(application: Application)
    fun threadExecutor(): ThreadExecutor
    fun postExecutionThread(): PostExecutionThread
    fun gson(): Gson
    fun context(): Context
    fun dataRepository(): DataRepositoryInterface
}
