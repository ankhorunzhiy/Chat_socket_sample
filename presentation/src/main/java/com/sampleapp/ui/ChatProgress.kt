package com.sampleapp.ui

import android.app.ProgressDialog
import android.content.Context
import android.view.Window
import com.android.newssample.R

class ChatProgress(context: Context?) : ProgressDialog(context) {

    init {
        isIndeterminate = true
        val progressText = context?.getString(R.string.please_wait)
        setMessage(progressText)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
    }
}