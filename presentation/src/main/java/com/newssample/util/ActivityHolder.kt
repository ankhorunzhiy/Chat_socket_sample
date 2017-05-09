package com.newssample.util

import android.app.Activity

import mortar.Presenter
import mortar.bundler.BundleService

class ActivityHolder : Presenter<Activity>() {

    override fun extractBundleService(view: Activity): BundleService {
        return BundleService.getBundleService(view)
    }

    fun activity(): Activity {
        return view
    }

    override fun dropView(view: Activity) {
        super.dropView(view)
    }
}
