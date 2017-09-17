package com.sampleapp.di.components

import com.sampleapp.MainActivity
import com.sampleapp.ui.controller.*
import com.sampleapp.di.ActivityScope
import com.sampleapp.di.module.ActivityModule

@ActivityScope
@dagger.Subcomponent(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {
    fun inject(mainActivity: MainActivity)
    fun loginBuilder(): LoginController.Component.Builder
    fun chatBuilder(): ChatController.Component.Builder
}