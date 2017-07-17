package com.sampleapp.ui.controller

import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler
import com.sampleapp.util.toFadeTransaction
import com.sampleapp.util.toHorizontalTransaction


class ControllerMediator constructor(val router: Router) {

    fun push(controller: Controller) {
        router.pushController(controller.toHorizontalTransaction())
    }

    fun setRoot(controller: Controller, addStartTransition: Boolean = false) {
        val popChangeHandler = RouterTransaction.with(controller).popChangeHandler(FadeChangeHandler())
        val routerTransaction = if (addStartTransition) controller.toHorizontalTransaction() else popChangeHandler
        router.setRoot(routerTransaction)
    }


}