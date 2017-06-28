package com.sampleapp.util

import com.sampleapp.ui.controller.Layout

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