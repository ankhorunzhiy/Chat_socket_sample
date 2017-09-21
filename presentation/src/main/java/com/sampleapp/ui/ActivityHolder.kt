package com.sampleapp.ui

import com.sampleapp.BaseActivity
import com.sampleapp.di.ActivityScope
import javax.inject.Inject

/**
 * Created by Tony on 21.09.17.
 */
@ActivityScope
class ActivityHolder @Inject constructor() {
    private var activity: BaseActivity? = null

    fun take(activity: BaseActivity){
        this.activity = activity
    }
    fun drop(){
        this.activity = null
    }

    fun get(): BaseActivity?{
        return activity
    }
}