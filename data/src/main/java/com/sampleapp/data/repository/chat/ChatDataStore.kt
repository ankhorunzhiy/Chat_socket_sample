package com.sampleapp.data.repository.chat

import com.sampleapp.data.model.EventDataModel
import com.sampleapp.domain.model.Event
import com.sampleapp.domain.model.Message
import rx.Observable

interface ChatDataStore {

    fun on(vararg events: Event): Observable<EventDataModel>

    fun sendMessage(message: Message): Observable<EventDataModel>
}