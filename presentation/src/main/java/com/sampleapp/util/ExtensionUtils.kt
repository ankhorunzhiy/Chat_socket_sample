package com.sampleapp.util

import android.app.ProgressDialog
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.sampleapp.domain.interactor.UseCase
import com.sampleapp.rx.SimpleSubscriber
import io.reactivex.FlowableSubscriber
import io.reactivex.disposables.CompositeDisposable


fun View.visible(isVisible: Boolean) {
    visibility = if (isVisible) View.VISIBLE else View.GONE
}

fun View.hideKeybord() {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imm.hideSoftInputFromWindow(windowToken, 0)
}

fun <T>SimpleSubscriber<T>.addTo(compositeDisposable: CompositeDisposable): SimpleSubscriber<T>{
    compositeDisposable.add(this)
    return this
}

fun ProgressDialog.toggle(show: Boolean){
    if(show){
        if(!isShowing) this.show()
    } else if(isShowing)
        this.dismiss()
}