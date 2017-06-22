package com.sampleapp.adapter

import com.bluelinelabs.conductor.Controller
import com.bluelinelabs.conductor.Router
import com.bluelinelabs.conductor.RouterTransaction
import com.bluelinelabs.conductor.support.RouterPagerAdapter
import com.sampleapp.controller.PagerFirstController
import com.sampleapp.controller.PagerSecondController

class ScreenAdapter(controller: Controller) : RouterPagerAdapter(controller) {

    enum class Screen{
        FIRST, SECOND
    }

    override fun configureRouter(router: Router?, position: Int) {
        if (router!= null && !router.hasRootController()) {
            val page = Screen.values()[position]
            when(page){
                Screen.FIRST -> router.setRoot(RouterTransaction.with(PagerFirstController()))
                Screen.SECOND -> router.setRoot(RouterTransaction.with(PagerSecondController()))
            }
        }
    }

    override fun getCount(): Int {
        return Screen.values().size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return Screen.values()[position].name
    }
}