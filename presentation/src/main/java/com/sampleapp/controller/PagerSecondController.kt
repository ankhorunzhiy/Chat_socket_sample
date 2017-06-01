package com.sampleapp.controller

import android.os.Bundle
import com.android.newssample.R
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.hannesdorfmann.mosby3.mvp.MvpView
import com.sampleapp.controller.DaggerPagerSecondController_Component.builder
import com.sampleapp.di.ScreenScope
import com.sampleapp.di.components.ActivityComponent
import javax.inject.Inject


/**
 * Created by Tony on 09.05.17.
 */

@Layout(R.layout.screen_second_pager)
class PagerSecondController(args: Bundle? = null) : BaseController<MvpView, PagerSecondController.Presenter>(args) {

    @Inject
    lateinit var overlayPresenter: Presenter

    @ScreenScope(PagerSecondController::class)
    @dagger.Component(dependencies = arrayOf(ActivityComponent::class))
    interface Component {
        fun inject(secondController: PagerSecondController)
    }

    override fun injectToDagger(component: ActivityComponent) {
        builder().activityComponent(component)
                .build()
                .inject(this)
    }

    override fun createPresenter(): Presenter {
        return overlayPresenter
    }

    @ScreenScope(PagerSecondController::class)
    class Presenter @Inject constructor() : MvpBasePresenter<MvpView>()
}