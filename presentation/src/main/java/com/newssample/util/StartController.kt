package com.newssample.util

import android.os.Bundle
import com.android.newssample.R
import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import com.hannesdorfmann.mosby3.mvp.MvpView
import com.newssample.BaseController
import com.newssample.MvpPresenterImpl

/**
 * Created by Tony on 09.05.17.
 */
@Layout(R.layout.screen_start)
class StartController(args: Bundle?) : BaseController<MvpView, MvpPresenter<MvpView>>(args) {
    override fun createPresenter(): MvpPresenter<MvpView> {
        return MvpPresenterImpl()
    }

}