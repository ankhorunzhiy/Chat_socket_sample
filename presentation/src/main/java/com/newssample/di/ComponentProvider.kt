package com.newssample.di

import com.newssample.di.components.ActivityComponent
import com.newssample.di.components.ControllerComponent

/**
 * Created by Anton Khorunzhiy on 5/29/17.
 */
interface ComponentProvider {

    fun activityComponent(): ActivityComponent
    fun controllerComponent(): ControllerComponent
}