package com.sampleapp.data.repository.chat

import com.sampleapp.data.model.mapper.EventDataMapper
import com.sampleapp.data.model.EventDataModel
import com.sampleapp.data.net.RestApi
import com.sampleapp.domain.model.Event
import com.sampleapp.domain.model.Message
import rx.Observable
import javax.inject.Inject

class RemoteChatDataStore @Inject constructor(val restApi: RestApi, val eventDataMapper: EventDataMapper): ChatDataStore{


    override fun on(vararg events: Event): Observable<EventDataModel> {
        if (events.isEmpty()) return Observable.empty()
        else return restApi.onEvents(*events).flatMap { Observable.just(eventDataMapper.map(it)) }
    }

    override fun sendMessage(message: Message): Observable<EventDataModel> {
        return restApi.sendMessage(message).flatMap { Observable.just(eventDataMapper.map(it)) }
    }
}