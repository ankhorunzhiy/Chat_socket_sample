package com.sampleapp.controller

import android.os.Bundle
import android.view.View
import com.android.newssample.R
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.hannesdorfmann.mosby3.mvp.MvpView
import com.sampleapp.adapter.ScreenAdapter
import com.sampleapp.controller.DaggerPagerRootController_Component.builder
import com.sampleapp.di.DaggerUtils

import com.sampleapp.di.ScreenScope
import com.sampleapp.di.components.ActivityComponent
import kotlinx.android.synthetic.main.screen_root_pager.view.*
import javax.inject.Inject

@Layout(R.layout.screen_root_pager)
class PagerRootController(args: Bundle? = null) : BaseController<MvpView, PagerRootController.Presenter>(args) {

    @Inject
    lateinit var controllerPresenter: Presenter

    @ScreenScope(PagerRootController::class)
    @dagger.Component(dependencies = arrayOf(ActivityComponent::class))
    interface Component {
        fun inject(secondController: PagerRootController)
    }

    override fun injectToDagger(component: ActivityComponent) {
        DaggerUtils.createComponent(Component::class.java, component).inject(this)
    }

    override fun createPresenter(): Presenter {
        return controllerPresenter
    }

    override fun onViewCreated(root: View) {
        super.onViewCreated(root)
        root.view_pager.adapter = ScreenAdapter(this)
        root.tab_layout.setupWithViewPager(root.view_pager)
    }

    @ScreenScope(PagerRootController::class)
    class Presenter @Inject constructor() : MvpBasePresenter<MvpView>()
}