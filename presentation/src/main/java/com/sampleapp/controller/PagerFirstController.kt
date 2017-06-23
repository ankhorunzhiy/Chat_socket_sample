package com.sampleapp.controller

import android.os.Bundle
import com.android.newssample.R
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.hannesdorfmann.mosby3.mvp.MvpView
import com.sampleapp.BaseActivity
import com.sampleapp.controller.DaggerPagerFirstController_Component.builder
import com.sampleapp.di.DaggerUtils
import com.sampleapp.di.ScreenScope
import com.sampleapp.di.components.ActivityComponent
import javax.inject.Inject

@Layout(R.layout.screen_first_pager)
class PagerFirstController(args: Bundle? = null) : BaseController<MvpView, PagerFirstController.Presenter>(args) {

    @Inject
    lateinit var controllerPresenter: Presenter

    @ScreenScope(PagerFirstController::class)
    @dagger.Component(dependencies = arrayOf(ActivityComponent::class))
    interface Component {
        fun inject(secondController: PagerFirstController)
    }

    override fun injectToDagger(component: ActivityComponent) {
        DaggerUtils.createComponent(Component::class.java, component).inject(this)
    }

    override fun createPresenter(): Presenter {
        return controllerPresenter
    }

    @ScreenScope(PagerFirstController::class)
    class Presenter @Inject constructor() : MvpBasePresenter<MvpView>()
}