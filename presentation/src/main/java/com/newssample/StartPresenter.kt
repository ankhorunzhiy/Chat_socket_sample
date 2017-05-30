package com.newssample

import com.google.gson.Gson
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.hannesdorfmann.mosby3.mvp.MvpView
import com.newssample.controller.StartController
import com.newssample.util.ScreenScope
import javax.inject.Inject

/**
 * Created by Tony on 09.05.17.
 */
@ScreenScope(StartController::class)
class StartPresenter @Inject constructor() : MvpBasePresenter<MvpView>(){

}