package com.sampleapp

import com.sampleapp.di.DaggerUtils
import com.sampleapp.di.components.ApplicationComponent
import com.sampleapp.di.module.ApplicationModule

class Application : android.app.Application() {

    lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerUtils.createComponent(ApplicationComponent::class.java, ApplicationModule())
        appComponent.inject(this)
    }

}
