package com.sampleapp

import com.sampleapp.di.components.ApplicationComponent
import com.sampleapp.di.components.DaggerApplicationComponent
import com.sampleapp.di.module.ApplicationModule

class Application : android.app.Application() {

    lateinit var applicationComponent: ApplicationComponent


    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
        applicationComponent.inject(this)
    }

}
