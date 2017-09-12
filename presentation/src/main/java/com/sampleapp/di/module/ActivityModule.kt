package com.sampleapp.di.module

import android.app.Activity
import android.view.LayoutInflater
import com.bluelinelabs.conductor.Router
import com.sampleapp.BaseActivity
import com.sampleapp.navigation.ControllerMediator
import com.sampleapp.di.ActivityScope
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(val activity: Activity) {

    @Provides
    fun provideLayoutInflater(): LayoutInflater {
        return LayoutInflater.from(activity)
    }

    @Provides
    @ActivityScope
    fun provideRouter(): Router {
        return (activity as BaseActivity).router
    }
}
