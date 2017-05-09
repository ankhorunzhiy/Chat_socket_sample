package com.newssample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.ButterKnife
import butterknife.Unbinder
import com.hannesdorfmann.mosby3.mvp.MvpPresenter
import com.hannesdorfmann.mosby3.mvp.MvpView
import com.hannesdorfmann.mosby3.mvp.conductor.MvpController
import com.evernote.android.state.StateSaver
import com.newssample.util.Layout


/**
 * Created by Tony on 09.05.17.
 */
abstract class BaseController<V : MvpView, P : MvpPresenter<V>>(args: Bundle?) : MvpController<V, P>(args) {

    private var unbinder: Unbinder? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup): View {
        val layout = getLayoutFromAnnotation(this.javaClass) ?:
                throw IllegalArgumentException("Controller should have Layout annotation")
        val root = inflater.inflate(layout.value, container, false)
        unbinder = ButterKnife.bind(this, root)
        return root
    }

    override fun onDestroyView(view: View) {
        super.onDestroyView(view)
        unbinder?.unbind()
        unbinder = null
    }

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