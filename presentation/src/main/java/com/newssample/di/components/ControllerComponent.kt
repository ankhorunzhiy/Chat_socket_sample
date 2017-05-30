package com.newssample.di.components

import com.newssample.controller.StartController
import com.newssample.di.module.ControllerModule
import com.newssample.util.ControllerScope
import dagger.Component

/**
 * Created by Anton Khorunzhiy on 5/29/17.
 */
@ControllerScope
@Component(dependencies = arrayOf(ActivityComponent::class), modules = arrayOf(ControllerModule::class))
interface ControllerComponent {
    fun inject(startController: StartController)
}