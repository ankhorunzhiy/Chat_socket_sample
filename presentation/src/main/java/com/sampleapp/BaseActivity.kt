package com.sampleapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.sampleapp.di.components.ActivityComponent
import com.sampleapp.di.module.ActivityModule

/**
 * Created by Anton Khorunzhiy on 5/31/17.
 */
open class BaseActivity : AppCompatActivity() {

    lateinit var component: ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prepareComponents()
    }

    private fun prepareComponents() {
        val applicationComponent = (applicationContext as Application).applicationComponent
        component = applicationComponent.plusActivityComponent(ActivityModule())
        component.inject(this)
    }
}