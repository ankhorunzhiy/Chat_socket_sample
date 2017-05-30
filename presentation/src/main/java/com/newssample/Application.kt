package com.newssample

import com.newssample.di.components.ApplicationComponent
import com.newssample.di.components.DaggerApplicationComponent
import com.newssample.di.module.ApplicationModule

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
