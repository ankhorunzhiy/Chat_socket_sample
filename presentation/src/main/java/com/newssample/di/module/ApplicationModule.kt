package com.newssample.di.module

import android.app.Application
import android.content.Context

import com.android.newssamle.domain.data.executor.PostExecutionThread
import com.android.newssamle.domain.data.executor.ThreadExecutor
import com.android.newssample.data.executor.JobExecutor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.newssample.util.ApplicationScope
import com.newssample.util.UIThread


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

}
