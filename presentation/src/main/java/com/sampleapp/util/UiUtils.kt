package com.sampleapp.util

import android.content.Context
import android.view.View
import com.sampleapp.ui.controller.Layout
import android.content.Context.INPUT_METHOD_SERVICE
import android.view.inputmethod.InputMethodManager


class UiUtils {

    companion object{
        fun getLayoutFromAnnotation(clazz: Class<*>?): Layout? {
            if (clazz == null || clazz == Any::class.java) return null

            val layout = clazz.getAnnotation(Layout::class.java)
            if (layout != null) {
                return layout
            } else {
                return getLayoutFromAnnotation(clazz.superclass)
            }
        }
    }
}