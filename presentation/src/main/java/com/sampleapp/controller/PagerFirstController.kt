package com.sampleapp.controller

import android.os.Bundle
import com.android.newssample.R
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.hannesdorfmann.mosby3.mvp.MvpView
import com.sampleapp.BaseActivity
import com.sampleapp.controller.DaggerPagerFirstController_Component.builder
import com.sampleapp.di.ScreenScope
import javax.inject.Inject


/**
 * Created by Tony on 09.05.17.
 */

@Layout(R.layout.screen_first_pager)
class PagerFirstController(args: Bundle? = null) : BaseController<MvpView, PagerFirstController.Presenter>(args) {

    @Inject
    lateinit var controllerPresenter: Presenter

    @ScreenScope(PagerFirstController::class)
    @dagger.Component(dependencies = arrayOf(BaseActivity.Component::class))
    interface Component {
        fun inject(secondController: PagerFirstController)
    }

    override fun injectToDagger(component: BaseActivity.Component) {
        builder().component(component)
                .build()
                .inject(this)
    }

    override fun createPresenter(): Presenter {
        return controllerPresenter
    }

    @ScreenScope(PagerFirstController::class)
    class Presenter @Inject constructor() : MvpBasePresenter<MvpView>()
}