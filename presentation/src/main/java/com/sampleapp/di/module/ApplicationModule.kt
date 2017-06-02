package com.sampleapp.di.module

import android.app.Application
import android.content.Context

import com.sampleapp.domain.data.executor.PostExecutionThread
import com.sampleapp.domain.data.executor.ThreadExecutor
import com.sampleapp.data.executor.JobExecutor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.sampleapp.di.ApplicationScope
import com.sampleapp.domain.data.repository.DataRepositoryImpl
import com.sampleapp.domain.data.repository.DataRepositoryInterface
import com.sampleapp.util.UIThread


import dagger.Module
import dagger.Provides


@Module
class ApplicationModule(private val application: Application) {


    @Provides
    @ApplicationScope
    internal fun provideThreadExecutor(): ThreadExecutor {
        return JobExecutor()
    }

    @Provides
    @ApplicationScope
    fun provideAppContext(): Context {
        return this.application
    }

    @Provides
    @ApplicationScope
    internal fun providePostExecutionThread(): PostExecutionThread {
        return UIThread()
    }

    @Provides
    @ApplicationScope
    internal fun provideGson(): Gson {
        val builder = GsonBuilder()
        return builder.create()
    }

    @Provides
    @ApplicationScope
    fun provideDataRepository(): DataRepositoryInterface{
        return DataRepositoryImpl()
    }


}
