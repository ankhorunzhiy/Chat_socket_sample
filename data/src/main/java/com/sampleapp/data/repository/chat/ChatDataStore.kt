package com.sampleapp.data.repository.chat

import com.sampleapp.data.model.EventDataModel
import com.sampleapp.domain.model.Event
import com.sampleapp.domain.model.Message
import io.reactivex.Completable
import io.reactivex.Flowable
import rx.Observable

interface ChatDataStore {

    fun on(vararg events: Event): Flowable<EventDataModel>

    fun sendMessage(message: Message): Flowable<EventDataModel>

    fun disconnect(): Completable
}