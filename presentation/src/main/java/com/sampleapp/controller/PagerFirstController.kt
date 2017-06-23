package com.sampleapp.controller

import android.os.Bundle
import com.android.newssample.R
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.hannesdorfmann.mosby3.mvp.MvpView
import com.sampleapp.di.ScreenScope
import com.sampleapp.di.components.ActivityComponent
import dagger.Subcomponent
import javax.inject.Inject

@Layout(R.layout.screen_first_pager)
class PagerFirstController(args: Bundle? = null) : BaseController<MvpView, PagerFirstController.Presenter>(args) {

    @Inject
    lateinit var controllerPresenter: Presenter

    @ScreenScope(PagerFirstController::class)
    @Subcomponent()
    interface Component {
        fun inject(secondController: PagerFirstController)
    }

    override fun injectToDagger(component: ActivityComponent) {
        component.pagerFirstScreenComponent().inject(this)
    }

    override fun createPresenter(): Presenter {
        return controllerPresenter
    }

    @ScreenScope(PagerFirstController::class)
    class Presenter @Inject constructor() : MvpBasePresenter<MvpView>()
}