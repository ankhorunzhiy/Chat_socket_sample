package com.newssample.controller

import android.os.Bundle
import com.android.newssample.R
import com.hannesdorfmann.mosby3.mvp.MvpView
import com.newssample.BaseController
import com.newssample.StartPresenter
import com.newssample.util.Layout

/**
 * Created by Tony on 09.05.17.
 */
@Layout(R.layout.screen_start)
class StartController(args: Bundle? = null) : BaseController<MvpView, StartPresenter>(args) {
    override fun createPresenter(): StartPresenter {
        return StartPresenter()
    }

}