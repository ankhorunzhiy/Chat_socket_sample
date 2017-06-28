package com.sampleapp.ui.view

import com.hannesdorfmann.mosby3.mvp.MvpView

interface BaseMvpView: MvpView {

    fun showProgress()
    fun hideProgress()
}