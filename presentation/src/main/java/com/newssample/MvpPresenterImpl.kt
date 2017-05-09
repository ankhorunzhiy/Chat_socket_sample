package com.newssample

import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import com.hannesdorfmann.mosby3.mvp.MvpView

/**
 * Created by Tony on 09.05.17.
 */
class MvpPresenterImpl: MvpPresenter<MvpView> {
    override fun detachView(retainInstance: Boolean) {
    }

    override fun attachView(view: MvpView?) {
    }
}