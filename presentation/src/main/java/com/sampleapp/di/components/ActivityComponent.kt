package com.sampleapp.di.components

import com.sampleapp.BaseActivity
import com.sampleapp.di.ActivityScope
import com.sampleapp.di.module.ActivityModule

@ActivityScope
@dagger.Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(ActivityModule::class))
interface ActivityComponent : ApplicationComponent {
    fun inject(baseActivity: BaseActivity)
}