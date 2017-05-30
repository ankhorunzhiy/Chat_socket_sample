package com.newssample

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.hannesdorfmann.mosby3.mvp.MvpView
import com.newssample.controller.StartController
import com.newssample.util.ScreenScope
import javax.inject.Inject


@ScreenScope(StartController::class)
class StartPresenter @Inject constructor() : MvpBasePresenter<MvpView>(){

}