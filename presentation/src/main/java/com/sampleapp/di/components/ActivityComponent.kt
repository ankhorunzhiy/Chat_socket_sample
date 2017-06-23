package com.sampleapp.di.components

import com.sampleapp.MainActivity
import com.sampleapp.controller.*
import com.sampleapp.di.ActivityScope
import com.sampleapp.di.module.ActivityModule

@ActivityScope
@dagger.Subcomponent(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {
    fun inject(mainActivity: MainActivity)
    fun startComponent(module: StartController.Module): StartController.Component
    fun overlayComponent(): OverlayController.Component
    fun pagerFirstScreenComponent(): PagerFirstController.Component
    fun pagerRootComponent(): PagerRootController.Component
    fun pagerSecondScreenComponent(): PagerSecondController.Component
}