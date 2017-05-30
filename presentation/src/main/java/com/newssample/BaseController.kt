package com.newssample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.evernote.android.state.StateSaver
import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import com.hannesdorfmann.mosby3.mvp.MvpView
import com.hannesdorfmann.mosby3.mvp.conductor.MvpController
import com.newssample.di.ComponentProvider
import com.newssample.di.components.ControllerComponent
import com.newssample.util.Layout


/**
 * Created by Tony on 09.05.17.
 */
abstract class BaseController<V : MvpView, P : MvpPresenter<V>>(args: Bundle?) : MvpController<V, P>(args) {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        prepareGraph()
        val layout = getLayoutFromAnnotation(this.javaClass) ?:
                throw IllegalArgumentException("Controller should have Layout annotation")
        val root = inflater.inflate(layout.value, container, false)
        return root
    }

    private fun prepareGraph() {
        val controllerComponent = (activity as ComponentProvider).controllerComponent()
        injectToDagger(controllerComponent)
    }

    abstract fun injectToDagger(controllerComponent: ControllerComponent)

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