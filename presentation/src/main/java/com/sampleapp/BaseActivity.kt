package com.sampleapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.sampleapp.di.ScreenScope
import com.sampleapp.di.components.ApplicationComponent
import com.sampleapp.di.module.ActivityModule

/**
 * Created by Anton Khorunzhiy on 5/31/17.
 */
open class BaseActivity : AppCompatActivity(){

    lateinit var component: Component

    @ScreenScope(BaseActivity::class)
    @dagger.Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(ActivityModule::class))
    interface Component : ApplicationComponent {
        fun inject(baseActivity: BaseActivity)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prepareComponents()
    }

    private fun prepareComponents() {
        val applicationComponent = (applicationContext as Application).applicationComponent
        component = DaggerBaseActivity_Component.builder()
                .applicationComponent(applicationComponent)
                .build()
        component.inject(this)
    }
}