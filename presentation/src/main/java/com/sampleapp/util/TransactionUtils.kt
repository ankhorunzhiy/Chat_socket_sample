package com.sampleapp.util

import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.ControllerChangeHandler
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler

/**
 * Created by Anton Khorunzhiy on 5/31/17.
 */

fun RouterTransaction.applyChangeHandler(controllerChangeHandler: ControllerChangeHandler): RouterTransaction{
    this.pushChangeHandler(controllerChangeHandler)
    this.popChangeHandler(controllerChangeHandler)
    return this
}

fun RouterTransaction.applyHorizontalHandler(removesFromViewOnPush: Boolean = true): RouterTransaction{
    this.applyChangeHandler(HorizontalChangeHandler(removesFromViewOnPush))
    return this
}

fun Controller.horizontalTransaction(removesFromViewOnPush: Boolean = true): RouterTransaction{
    return RouterTransaction.with(this).applyHorizontalHandler(removesFromViewOnPush)
}