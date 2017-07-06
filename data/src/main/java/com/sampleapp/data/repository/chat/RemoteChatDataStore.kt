package com.sampleapp.data.repository.chat

import com.sampleapp.data.model.mapper.EventDataMapper
import com.sampleapp.data.model.EventDataModel
import com.sampleapp.data.net.RestApi
import rx.Observable
import javax.inject.Inject

class RemoteChatDataStore @Inject constructor(val restApi: RestApi, val eventDataMapper: EventDataMapper): ChatDataStore{
    override fun on(vararg events: String): Observable<EventDataModel> {
        if (events.isEmpty()) return Observable.empty()
        else return restApi.onEvents(events).flatMap { Observable.just(eventDataMapper.map(it)) }
    }
}