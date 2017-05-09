package com.newssample

import com.newssample.di.components.ApplicationComponent
import com.newssample.di.module.ApplicationModule


import io.techery.presenta.mortar.DaggerService
import mortar.MortarScope


class Application : android.app.Application() {

    lateinit var applicationComponent: ApplicationComponent

    private var rootScope: MortarScope? = null

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerService.createComponent(ApplicationComponent::class.java, ApplicationModule(this))
        applicationComponent.inject(this)
    }

    override fun getSystemService(name: String): Any {
        if (rootScope == null) {
            rootScope = MortarScope.buildRootScope()
                    .withService(DaggerService.SERVICE_NAME, applicationComponent)
                    .build("root")
        }
        if (rootScope!!.hasService(name)) return rootScope!!.getService<Any>(name)

        return super.getSystemService(name)
    }


}
