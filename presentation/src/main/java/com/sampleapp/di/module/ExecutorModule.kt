package com.sampleapp.di.module

import com.sampleapp.data.executor.JobExecutor
import com.sampleapp.domain.data.executor.PostExecutionThread
import com.sampleapp.domain.data.executor.ThreadExecutor
import com.sampleapp.domain.data.executor.WorkExecutionThread
import com.sampleapp.util.UIThread
import com.sampleapp.util.WorkThread
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface ExecutorModule {

    @Singleton
    @Binds
    fun provideThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor

    @Singleton
    @Binds
    fun provideWorkExecutoionThread(workThread: WorkThread): WorkExecutionThread

    @Singleton
    @Binds
    fun providePostExecutionThread(uiThread: UIThread): PostExecutionThread
}