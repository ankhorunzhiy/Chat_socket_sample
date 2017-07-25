package com.sampleapp.data.repository.chat

import com.sampleapp.data.model.mapper.EventDataMapper
import com.sampleapp.data.model.EventDataModel
import com.sampleapp.data.net.RestApi
import com.sampleapp.domain.model.Event
import com.sampleapp.domain.model.Message
import io.reactivex.Completable
import io.reactivex.Flowable
import rx.Observable
import javax.inject.Inject

class RemoteChatDataStore @Inject constructor(val restApi: RestApi, val eventDataMapper: EventDataMapper): ChatDataStore{

    override fun on(vararg events: Event): Flowable<EventDataModel> {
        if (events.isEmpty()) return Flowable.empty()
        else return restApi.onEvents(*events).flatMap { Flowable.just(eventDataMapper.map(it)) }
    }

    override fun sendMessage(message: Message): Flowable<EventDataModel> {
        return restApi.sendMessage(message).flatMap { Flowable.just(eventDataMapper.map(it)) }
    }

    override fun disconnect(): Completable {
        return restApi.disconnect()
    }
}