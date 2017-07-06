package com.sampleapp.data.repository.chat

import com.sampleapp.data.model.mapper.EventMapper
import com.sampleapp.data.repository.user.ChatDataStoreFactory
import com.sampleapp.domain.model.Event
import com.sampleapp.domain.model.EventModel
import com.sampleapp.domain.repository.ChatRepository
import rx.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChatDataRepository @Inject constructor(val chatDataStoreFactory: ChatDataStoreFactory,
                                             val eventMapper: EventMapper) : ChatRepository {

    override fun on(vararg events: Event): Observable<EventModel> {
        return chatDataStoreFactory.createChatStore().on(*events)
                .flatMap { Observable.just(eventMapper.transform(it)) }
    }
}