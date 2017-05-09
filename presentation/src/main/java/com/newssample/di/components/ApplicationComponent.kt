package com.newssample.di.components

import com.android.newssamle.domain.data.executor.PostExecutionThread
import com.android.newssamle.domain.data.executor.ThreadExecutor
import com.google.gson.Gson
import com.newssample.Application
import com.newssample.di.module.ApplicationModule
import com.newssample.util.ActivityHolder

import dagger.Component
import io.techery.presenta.addition.ActionBarOwner
import io.techery.presenta.di.ApplicationScope

@ApplicationScope
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {
    fun inject(application: Application)
    fun actionBarTuner(): ActionBarOwner
    fun activityHolder(): ActivityHolder
    fun threadExecutor(): ThreadExecutor
    fun postExecutionThread(): PostExecutionThread
    fun gson(): Gson
}
