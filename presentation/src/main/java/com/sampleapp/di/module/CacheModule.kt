package com.sampleapp.di.module

import com.sampleapp.data.cache.FlowableCacher
import com.sampleapp.data.cache.FlowableCacherImpl
import dagger.Binds
import dagger.Module
import javax.inject.Singleton


@Module
interface CacheModule{

    @Singleton
    @Binds
    fun provideCacher(flowableCacherImpl: FlowableCacherImpl): FlowableCacher
}
