package com.sampleapp.navigation

import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.changehandler.FadeChangeHandler
import com.bluelinelabs.conductor.changehandler.HorizontalChangeHandler
import com.sampleapp.util.toFadeTransaction
import com.sampleapp.util.toHorizontalTransaction
import javax.inject.Inject
import javax.inject.Provider


class ControllerMediator @Inject constructor(val router: Provider<Router>) {

    fun push(controller: Controller) {
        router.get().pushController(controller.toHorizontalTransaction())
    }

    fun setRoot(controller: Controller, addStartTransition: Boolean = false) {
        val routerActivity = router.get() ?: return
        routerActivity.activity?.runOnUiThread({
            val popChangeHandler = RouterTransaction.with(controller).popChangeHandler(FadeChangeHandler())
            val routerTransaction = if (addStartTransition) controller.toHorizontalTransaction() else popChangeHandler
            routerActivity.setRoot(routerTransaction)
        })

    }
}