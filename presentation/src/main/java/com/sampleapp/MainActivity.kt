package com.sampleapp

import android.os.Bundle
import com.android.newssample.R
import com.bluelinelabs.conductor.Conductor
import com.sampleapp.navigation.ControllerMediator
import com.sampleapp.ui.controller.Layout
import com.sampleapp.ui.controller.LoginController
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import javax.inject.Inject

@Layout(R.layout.app_bar_main)
class MainActivity : BaseActivity() {

    @Inject
    lateinit var mediator: ControllerMediator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)
        prepareUI()
        if(savedInstanceState == null)
            mediator.setRoot(LoginController()) // ToDo prepare navigation
    }

    override fun initRouter(savedInstanceState: Bundle?) {
        router = Conductor.attachRouter(this, controller_container, savedInstanceState)
    }

    private fun prepareUI() {
        setSupportActionBar(toolbar)
    }

    override fun onBackPressed() {
        if (!router.handleBack()) {
            super.onBackPressed()
        }
    }

}
