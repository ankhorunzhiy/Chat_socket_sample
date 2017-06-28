package com.sampleapp.di.components

import com.sampleapp.MainActivity
import com.sampleapp.ui.controller.*
import com.sampleapp.di.ActivityScope
import com.sampleapp.di.module.ActivityModule

@ActivityScope
@dagger.Subcomponent(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {
    fun inject(mainActivity: MainActivity)
    fun startComponent(): LoginController.Component
    fun overlayComponent(): OverlayController.Component
    fun pagerFirstScreenComponent(): PagerFirstController.Component
    fun pagerRootComponent(): PagerRootController.Component
    fun pagerSecondScreenComponent(): PagerSecondController.Component
}