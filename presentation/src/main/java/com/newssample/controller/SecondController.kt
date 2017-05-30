package com.newssample.controller

import android.os.Bundle

import com.android.newssample.R
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.hannesdorfmann.mosby3.mvp.MvpView
import com.newssample.BaseController
import com.newssample.MainActivity
import com.newssample.controller.DaggerSecondController_Component.builder
import com.newssample.util.Layout
import com.newssample.util.ScreenScope
import dagger.Provides
import javax.inject.Inject


/**
 * Created by Tony on 09.05.17.
 */

@Layout(R.layout.screen_second)
class SecondController(args: Bundle? = null) : BaseController<MvpView, SecondController.Presenter>(args) {

    @Inject
    lateinit var secondPresenter: Presenter

    @ScreenScope(SecondController::class)
    @dagger.Component(dependencies = arrayOf(MainActivity.Component::class), modules = arrayOf(Module::class))
    interface Component {
        fun inject(secondController: SecondController)
    }

    @ScreenScope(SecondController::class)
    @dagger.Module
    class Module {

        @Provides
        @ScreenScope(SecondController::class)
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

    @ScreenScope(SecondController::class)
    class Presenter @Inject constructor() : MvpBasePresenter<MvpView>()
}