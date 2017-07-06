package com.sampleapp.ui.view

import com.sampleapp.domain.model.EventModel

interface ChatView : BaseMvpView {

    fun notifyAdapter(eventModel: EventModel)
}