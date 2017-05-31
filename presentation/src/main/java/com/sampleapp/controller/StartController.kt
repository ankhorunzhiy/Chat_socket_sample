package com.sampleapp.controller

import android.os.Bundle
import android.view.View
import com.android.newssample.R
import com.bluelinelabs.conductor.RouterTransaction
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.hannesdorfmann.mosby3.mvp.MvpView
import com.sampleapp.BaseController
import com.sampleapp.MainActivity
import com.sampleapp.controller.DaggerStartController_Component.builder
import com.sampleapp.di.ScreenScope
import com.sampleapp.util.applyHorizontalHandler
import dagger.Provides
import kotlinx.android.synthetic.main.screen_start.view.*
import javax.inject.Inject


/**
 * Created by Tony on 09.05.17.
 */

@Layout(R.layout.screen_start)
class StartController(args: Bundle? = null) : BaseController<MvpView, StartController.Presenter>(args) {

    @Inject
    lateinit var startPresenter: Presenter

    @ScreenScope(StartController::class)
    @dagger.Component(dependencies = arrayOf(MainActivity.Component::class), modules = arrayOf(Module::class))
    interface Component {
        fun inject(startController: StartController)

        fun inject(presenter: Presenter)
    }

    @ScreenScope(StartController::class)
    @dagger.Module
    class Module {

        @Provides
        @ScreenScope(StartController::class)
        fun providePresenter(): Presenter {
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
        return startPresenter
    }


    override fun onViewCreated(root: View) {
        root.startText.setOnClickListener {
            getChildRouter(root.nestedLayout)
                    .pushController(RouterTransaction.with(OverlayController())
                    .applyHorizontalHandler(false))
        }
    }

    @ScreenScope(StartController::class)
    class Presenter @Inject constructor() : MvpBasePresenter<MvpView>()

}