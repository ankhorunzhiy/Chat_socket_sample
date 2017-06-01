package com.sampleapp.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.evernote.android.state.StateSaver
import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import com.hannesdorfmann.mosby3.mvp.MvpView
import com.hannesdorfmann.mosby3.mvp.conductor.MvpController
import com.sampleapp.BaseActivity
import com.sampleapp.MainActivity
import com.sampleapp.controller.Layout
import com.sampleapp.di.components.ActivityComponent


/**
 * Created by Tony on 09.05.17.
 */
abstract class BaseController<V : MvpView, P : MvpPresenter<V>>(args: Bundle?) : MvpController<V, P>(args) {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        prepareGraph()
        val layout = getLayoutFromAnnotation(this.javaClass) ?:
                throw IllegalArgumentException("Controller should have Layout annotation")
        val root = inflater.inflate(layout.value, container, false)
        onViewCreated(root)
        return root
    }

    private fun prepareGraph() {
        injectToDagger((activity as BaseActivity).component)
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

    private fun getLayoutFromAnnotation(clazz: Class<*>?): Layout? {
        if (clazz == null || clazz == Any::class.java) return null

        val layout = clazz.getAnnotation(Layout::class.java)
        if (layout != null) {
            return layout
        } else {
            return getLayoutFromAnnotation(clazz.superclass)
        }
    }

}