package com.sampleapp

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.SpannableString
import android.text.Spanned
import android.text.style.AbsoluteSizeSpan
import android.view.Window
import com.android.newssample.R
import com.bluelinelabs.conductor.Router
import com.sampleapp.di.components.ActivityComponent
import com.sampleapp.di.module.ActivityModule
import com.sampleapp.util.UiUtils


abstract class BaseActivity : AppCompatActivity() {

    lateinit var component: ActivityComponent
    lateinit var router: Router
    lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layout = UiUtils.getLayoutFromAnnotation(this.javaClass) ?:
                throw IllegalArgumentException("Activity should have Layout annotation")
        setContentView(layout.value)
        initRouter(savedInstanceState)
        initProgressDialog(true)
        prepareComponents()
    }

    abstract fun initRouter(savedInstanceState: Bundle?)

    private fun prepareComponents() {
        val applicationComponent = (applicationContext as Application).appComponent
        component = applicationComponent.plusActivityComponent(ActivityModule(this))
    }

    fun initProgressDialog(isNotCancelable: Boolean) {
        if (isFinishing) {
            return
        }
        progressDialog = ProgressDialog(this)
        progressDialog.isIndeterminate = true
        val progressText = resources.getString(R.string.please_wait)
        progressDialog.setMessage(progressText)
        progressDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        if (isNotCancelable) {
            progressDialog.setCancelable(false)
        }
    }

    fun showProgressDialog(cancelable: Boolean = false) {
        if (!isFinishing) {
            toggleProgressDialog(true, cancelable)
        }
    }

    fun hideProgressDialog() {
        toggleProgressDialog(false, false)
    }

    private fun toggleProgressDialog(show: Boolean, cancelable: Boolean) {
        if (isFinishing) {
            return
        }
        try {
            progressDialog.setCancelable(cancelable)
            if (show) {
                if (!progressDialog.isShowing()) {
                    progressDialog.show()
                }
            } else {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

}