package com.sampleapp.di.components

import com.sampleapp.Application
import com.sampleapp.di.module.ActivityModule
import com.sampleapp.di.module.ApplicationModule
import com.sampleapp.di.module.ExecutorModule
import com.sampleapp.di.module.RepositoryModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class, ExecutorModule::class, RepositoryModule::class))
interface ApplicationComponent {
    fun inject(application: Application)
    fun plusActivityComponent(module: ActivityModule): ActivityComponent
}
