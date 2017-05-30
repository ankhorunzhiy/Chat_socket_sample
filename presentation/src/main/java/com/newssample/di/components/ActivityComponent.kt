package com.newssample.di.components

import android.view.LayoutInflater
import com.newssample.MainActivity
import com.newssample.di.module.ActivityModule
import com.newssample.util.ActivityScope
import dagger.Component

/**
 * Created by Anton Khorunzhiy on 5/29/17.
 */
@ActivityScope
@Component(dependencies = arrayOf(ApplicationComponent::class), modules = arrayOf(ActivityModule::class))
interface ActivityComponent : ApplicationComponent{
    fun inject(mainActivity: MainActivity)
    fun inflater(): LayoutInflater
}