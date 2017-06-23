package com.sampleapp.controller

import android.os.Bundle
import com.android.newssample.R
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.hannesdorfmann.mosby3.mvp.MvpView
import com.sampleapp.di.ScreenScope
import com.sampleapp.di.components.ActivityComponent
import dagger.Subcomponent
import javax.inject.Inject

@Layout(R.layout.screen_second)
class OverlayController(args: Bundle? = null) : BaseController<MvpView, OverlayController.Presenter>(args) {

    @Inject
    lateinit var overlayPresenter: Presenter

    @ScreenScope(OverlayController::class)
    @Subcomponent()
    interface Component {
        fun inject(secondController: OverlayController)
    }

    override fun injectToDagger(component: ActivityComponent) {
        component.overlayComponent().inject(this)
    }

    override fun createPresenter(): Presenter {
        return overlayPresenter
    }

    @ScreenScope(OverlayController::class)
    class Presenter @Inject constructor() : MvpBasePresenter<MvpView>()
}