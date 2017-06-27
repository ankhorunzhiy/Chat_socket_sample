package com.sampleapp.util

import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.ControllerChangeHandler
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler

fun RouterTransaction.applyChangeHandler(controllerChangeHandler: ControllerChangeHandler): RouterTransaction{
    this.pushChangeHandler(controllerChangeHandler)
    this.popChangeHandler(controllerChangeHandler)
    return this
}

fun RouterTransaction.applyHorizontalHandler(removesFromViewOnPush: Boolean = true): RouterTransaction{
    this.applyChangeHandler(HorizontalChangeHandler(removesFromViewOnPush))
    return this
}

fun RouterTransaction.applyFadeHandler(removesFromViewOnPush: Boolean = true): RouterTransaction{
    this.applyChangeHandler(com.bluelinelabs.conductor.changehandler.FadeChangeHandler(removesFromViewOnPush))
    return this
}

fun Controller.toHorizontalTransaction(removesFromViewOnPush: Boolean = true): RouterTransaction{
    return RouterTransaction.with(this).applyHorizontalHandler(removesFromViewOnPush)
}

fun Controller.toFadeTransaction(removesFromViewOnPush: Boolean = true): RouterTransaction{
    return RouterTransaction.with(this).applyFadeHandler(removesFromViewOnPush)
}