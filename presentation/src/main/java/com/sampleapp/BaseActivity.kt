package com.sampleapp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.bluelinelabs.conductor.Router
import com.sampleapp.di.components.ActivityComponent
import com.sampleapp.di.module.ActivityModule
import com.sampleapp.util.UiUtils


abstract class BaseActivity : AppCompatActivity() {

    lateinit var component: ActivityComponent
    lateinit var router: Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layout = UiUtils.getLayoutFromAnnotation(this.javaClass) ?:
                throw IllegalArgumentException("Activity should have Layout annotation")
        setContentView(layout.value)
        initRouter(savedInstanceState)
        prepareComponents()
    }

    abstract fun initRouter(savedInstanceState: Bundle?)

    private fun prepareComponents() {
        val applicationComponent = (applicationContext as Application).appComponent
        component = applicationComponent.plusActivityComponent(ActivityModule(this))
    }
}