package com.sampleapp.data.repository.user

import com.sampleapp.data.model.mapper.EventDataMapper
import com.sampleapp.data.net.RestApiImpl
import com.sampleapp.data.net.SocketProviderImpl
import com.sampleapp.data.repository.chat.ChatDataStore
import com.sampleapp.data.repository.chat.RemoteChatDataStore
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ChatDataStoreFactory @Inject constructor(val socketProvider: SocketProviderImpl, val eventDataMapper: EventDataMapper) {

    fun createChatStore(): ChatDataStore {
        val restApi = RestApiImpl(socketProvider.provideSocket())
        return RemoteChatDataStore(restApi, eventDataMapper)
    }
}