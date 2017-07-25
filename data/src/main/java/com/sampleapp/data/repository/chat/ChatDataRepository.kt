package com.sampleapp.data.repository.chat

import com.sampleapp.data.model.mapper.EventMapper
import com.sampleapp.data.repository.user.ChatDataStoreFactory
import com.sampleapp.domain.model.Event
import com.sampleapp.domain.model.EventModel
import com.sampleapp.domain.model.Message
import com.sampleapp.domain.repository.ChatRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import rx.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChatDataRepository @Inject constructor(val chatDataStoreFactory: ChatDataStoreFactory,
                                             val eventMapper: EventMapper) : ChatRepository {

    override fun on(vararg events: Event): Flowable<EventModel> {
        return chatDataStoreFactory.createChatStore().on(*events)
                .flatMap { Flowable.just(eventMapper.transform(it)) }
    }

    override fun sendMessage(message: Message): Flowable<EventModel> {
        return chatDataStoreFactory.createChatStore().sendMessage(message)
                .flatMap { Flowable.just(eventMapper.transform(it)) }
    }

    override fun disconnect(): Completable {
        return chatDataStoreFactory.createChatStore().disconnect()
    }
}