package com.newssample.controller

import android.os.Bundle
import android.view.LayoutInflater
import com.android.newssample.R
import com.hannesdorfmann.mosby3.mvp.MvpView
import com.newssample.BaseController
import com.newssample.StartPresenter
import com.newssample.di.components.ControllerComponent
import com.newssample.util.Layout
import javax.inject.Inject

/**
 * Created by Tony on 09.05.17.
 */
@Layout(R.layout.screen_start)
class StartController(args: Bundle? = null) : BaseController<MvpView, StartPresenter>(args) {
    @Inject
    lateinit var inflater: LayoutInflater
    @Inject
    lateinit var startPresenter: StartPresenter


    override fun injectToDagger(controllerComponent: ControllerComponent) {
        controllerComponent.inject(this)
    }

    override fun createPresenter(): StartPresenter {
        return startPresenter
    }

}