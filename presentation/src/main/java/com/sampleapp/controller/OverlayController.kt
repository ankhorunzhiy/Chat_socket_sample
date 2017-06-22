package com.sampleapp.controller

import android.os.Bundle
import com.android.newssample.R
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.hannesdorfmann.mosby3.mvp.MvpView
import com.sampleapp.controller.DaggerOverlayController_Component.builder
import com.sampleapp.di.ScreenScope
import com.sampleapp.di.components.ActivityComponent
import javax.inject.Inject

@Layout(R.layout.screen_second)
class OverlayController(args: Bundle? = null) : BaseController<MvpView, OverlayController.Presenter>(args) {

    @Inject
    lateinit var overlayPresenter: Presenter

    @ScreenScope(OverlayController::class)
    @dagger.Component(dependencies = arrayOf(ActivityComponent::class))
    interface Component {
        fun inject(secondController: OverlayController)
    }

    override fun injectToDagger(component: ActivityComponent) {
        builder().activityComponent(component)
                .build()
                .inject(this)
    }

    override fun createPresenter(): Presenter {
        return overlayPresenter
    }

    @ScreenScope(OverlayController::class)
    class Presenter @Inject constructor() : MvpBasePresenter<MvpView>()
}