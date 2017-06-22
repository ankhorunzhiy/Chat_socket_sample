package com.sampleapp.controller

import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler
import com.sampleapp.util.toHorizontalTransaction


class ControllerMediator constructor(val router: Router) {

    fun push(controller: Controller) {
        router.pushController(controller.toHorizontalTransaction())
    }

    fun setRoot(controller: Controller) {
        if (!router.hasRootController()) {
            router.setRoot(RouterTransaction.with(controller).popChangeHandler(HorizontalChangeHandler()))
        }
    }
}