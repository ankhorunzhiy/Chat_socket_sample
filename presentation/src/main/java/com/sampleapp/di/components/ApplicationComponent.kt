package com.sampleapp.di.components

import com.sampleapp.Application
import com.sampleapp.di.module.*
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class, ExecutorModule::class,
        RepositoryModule::class, CacheModule::class))
interface ApplicationComponent {
    fun inject(application: Application)
    fun plusActivityComponent(module: ActivityModule): ActivityComponent
}
