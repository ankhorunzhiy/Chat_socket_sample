package com.newssample.controller

import android.os.Bundle

import com.android.newssample.R
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.hannesdorfmann.mosby3.mvp.MvpView
import com.newssample.BaseController
import com.newssample.MainActivity
import com.newssample.controller.DaggerOverlayController_Component.builder

import com.newssample.util.Layout
import com.newssample.util.ScreenScope
import dagger.Provides
import javax.inject.Inject


/**
 * Created by Tony on 09.05.17.
 */

@Layout(R.layout.screen_second)
class OverlayController(args: Bundle? = null) : BaseController<MvpView, OverlayController.Presenter>(args) {

    @Inject
    lateinit var secondPresenter: Presenter

    @ScreenScope(OverlayController::class)
    @dagger.Component(dependencies = arrayOf(MainActivity.Component::class), modules = arrayOf(Module::class))
    interface Component {
        fun inject(secondController: OverlayController)
    }

    @ScreenScope(OverlayController::class)
    @dagger.Module
    class Module {

        @Provides
        @ScreenScope(OverlayController::class)
        fun providePresenter(): Presenter{
            return Presenter()
        }
    }

    override fun injectToDagger(component: MainActivity.Component) {
        builder().component(component)
                .module(Module())
                .build()
                .inject(this)
    }

    override fun createPresenter(): Presenter {
        return secondPresenter
    }

    @ScreenScope(OverlayController::class)
    class Presenter @Inject constructor() : MvpBasePresenter<MvpView>()
}