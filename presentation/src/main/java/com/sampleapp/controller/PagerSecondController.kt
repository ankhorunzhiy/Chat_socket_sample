package com.sampleapp.controller

import android.os.Bundle
import com.android.newssample.R
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.hannesdorfmann.mosby3.mvp.MvpView
import com.sampleapp.BaseActivity
import com.sampleapp.controller.DaggerPagerSecondController_Component.builder
import com.sampleapp.di.ScreenScope
import javax.inject.Inject


/**
 * Created by Tony on 09.05.17.
 */

@Layout(R.layout.screen_second_pager)
class PagerSecondController(args: Bundle? = null) : BaseController<MvpView, PagerSecondController.Presenter>(args) {

    @Inject
    lateinit var overlayPresenter: Presenter

    @ScreenScope(PagerSecondController::class)
    @dagger.Component(dependencies = arrayOf(BaseActivity.Component::class))
    interface Component {
        fun inject(secondController: PagerSecondController)
    }

    override fun injectToDagger(component: BaseActivity.Component) {
        builder().component(component)
                .build()
                .inject(this)
    }

    override fun createPresenter(): Presenter {
        return overlayPresenter
    }

    @ScreenScope(PagerSecondController::class)
    class Presenter @Inject constructor() : MvpBasePresenter<MvpView>()
}