package com.sampleapp.controller

import android.os.Bundle
import android.view.View
import com.android.newssample.R
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.hannesdorfmann.mosby3.mvp.MvpView
import com.sampleapp.BaseActivity
import com.sampleapp.adapter.ScreenAdapter
import com.sampleapp.controller.DaggerPagerRootScreen_Component.builder
import com.sampleapp.di.ScreenScope
import kotlinx.android.synthetic.main.screen_root_pager.view.*
import javax.inject.Inject

/**
 * Created by Anton Khorunzhiy on 5/31/17.
 */
@Layout(R.layout.screen_root_pager)
class PagerRootScreen (args: Bundle? = null) : BaseController<MvpView, PagerRootScreen.Presenter>(args) {

    @Inject
    lateinit var controllerPresenter: Presenter

    @ScreenScope(PagerRootScreen::class)
    @dagger.Component(dependencies = arrayOf(BaseActivity.Component::class))
    interface Component {
        fun inject(secondController: PagerRootScreen)
    }

    override fun injectToDagger(component: BaseActivity.Component) {
        builder().component(component)
                .build()
                .inject(this)
    }

    override fun createPresenter(): Presenter {
        return controllerPresenter
    }

    override fun onViewCreated(root: View) {
        super.onViewCreated(root)
        root.view_pager.adapter = ScreenAdapter(this)
        root.tab_layout.setupWithViewPager(root.view_pager)
    }

    @ScreenScope(PagerRootScreen::class)
    class Presenter @Inject constructor() : MvpBasePresenter<MvpView>()
}