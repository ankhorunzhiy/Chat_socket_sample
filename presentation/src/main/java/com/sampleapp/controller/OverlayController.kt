package com.sampleapp.controller

import android.os.Bundle
import com.android.newssample.R

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.hannesdorfmann.mosby3.mvp.MvpView
import com.sampleapp.BaseActivity
import com.sampleapp.controller.BaseController
import com.sampleapp.MainActivity
import com.sampleapp.controller.DaggerOverlayController_Component.builder
import com.sampleapp.di.ScreenScope

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
    @dagger.Component(dependencies = arrayOf(BaseActivity.Component::class), modules = arrayOf(Module::class))
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

    override fun injectToDagger(component: BaseActivity.Component) {
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