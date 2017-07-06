package com.sampleapp.data.repository.chat

import com.sampleapp.data.model.EventDataModel
import rx.Observable

interface ChatDataStore {

    fun on(vararg events: String): Observable<EventDataModel>
}