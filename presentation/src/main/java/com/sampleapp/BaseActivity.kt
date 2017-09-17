package com.sampleapp

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Window
import com.android.newssample.R
import com.bluelinelabs.conductor.Router
import com.sampleapp.di.components.ActivityComponent
import com.sampleapp.di.module.ActivityModule
import com.sampleapp.ui.ChatProgress
import com.sampleapp.util.UiUtils
import com.sampleapp.util.toggle


abstract class BaseActivity : AppCompatActivity() {

    lateinit var component: ActivityComponent
    lateinit var router: Router
    var progressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prepareComponents()
        val layout = UiUtils.getLayoutFromAnnotation(this.javaClass) ?:
                throw IllegalArgumentException("Activity should have Layout annotation")
        setContentView(layout.value)
        initRouter(savedInstanceState)
    }

    abstract fun initRouter(savedInstanceState: Bundle?)

    private fun prepareComponents() {
        val applicationComponent = (applicationContext as Application).appComponent
        component = applicationComponent.plusActivityComponent(ActivityModule(this))
    }

    fun initProgressDialog() {
        if (isFinishing) {
            return
        }
        progressDialog = ChatProgress(this)
        progressDialog?.setCancelable(true)
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
        if(progressDialog == null)
            initProgressDialog()
        try {
            progressDialog?.setCancelable(cancelable)
            progressDialog?.toggle(show)
        } catch (e: Exception) {
            e.printStackTrace()
        }

    }

}