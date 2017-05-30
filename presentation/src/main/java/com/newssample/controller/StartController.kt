package com.newssample.controller

import android.os.Bundle
import com.android.newssample.R
import com.hannesdorfmann.mosby3.mvp.MvpView
import com.newssample.BaseController
import com.newssample.MainActivity
import com.newssample.StartPresenter
import com.newssample.controller.DaggerStartController_Component.*
import com.newssample.util.Layout
import com.newssample.util.ScreenScope
import dagger.Provides
import javax.inject.Inject

/**
 * Created by Tony on 09.05.17.
 */

@Layout(R.layout.screen_start)
class StartController(args: Bundle? = null) : BaseController<MvpView, StartPresenter>(args) {

    @Inject
    lateinit var startPresenter: StartPresenter

    @ScreenScope(StartController::class)
    @dagger.Component(dependencies = arrayOf(MainActivity.Component::class), modules = arrayOf(Module::class))
    interface Component {
        fun inject(startController: StartController)

        fun inject(presenter: StartPresenter)
    }

    @ScreenScope(StartController::class)
    @dagger.Module
    class Module {

        @Provides
        @ScreenScope(StartController::class)
        fun providePresenter(): StartPresenter{
            return StartPresenter()
        }
    }

    override fun injectToDagger(component: MainActivity.Component) {
        builder().component(component)
                .module(Module())
                .build().inject(this)
    }

    override fun createPresenter(): StartPresenter {
        return startPresenter
    }

}