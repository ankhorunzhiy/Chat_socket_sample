package com.sampleapp.ui.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.evernote.android.state.StateSaver
import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import com.hannesdorfmann.mosby3.mvp.MvpView
import com.hannesdorfmann.mosby3.mvp.conductor.MvpController
import com.sampleapp.BaseActivity
import com.sampleapp.di.components.ActivityComponent
import com.sampleapp.ui.view.BaseMvpView
import com.sampleapp.util.UiUtils

abstract class BaseController<V : BaseMvpView, P : MvpPresenter<V>>(args: Bundle?) : MvpController<V, P>(args), BaseMvpView {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        injectToDagger((activity as BaseActivity).component)
        val layout = UiUtils.getLayoutFromAnnotation(this.javaClass) ?:
                throw IllegalArgumentException("Controller should have Layout annotation")
        val root = inflater.inflate(layout.value, container, false)
        onViewCreated(root)
        return root
    }

    abstract fun injectToDagger(component: ActivityComponent)

    open fun onViewCreated(root: View){}

    override fun onSaveViewState(view: View, outState: Bundle) {
        super.onSaveViewState(view, outState)
        StateSaver.saveInstanceState(this, outState)
    }

    override fun onRestoreViewState(view: View, savedViewState: Bundle) {
        super.onRestoreViewState(view, savedViewState)
        StateSaver.restoreInstanceState(this, savedViewState)
    }

    override fun showProgress(){
        (activity as BaseActivity).showProgressDialog()
    }

    override fun hideProgress(){
        (activity as BaseActivity).hideProgressDialog()
    }


}