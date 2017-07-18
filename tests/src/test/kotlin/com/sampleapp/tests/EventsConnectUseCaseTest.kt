package com.sampleapp.tests

import com.sampleapp.domain.interactor.EventsConnectUseCase
import com.sampleapp.domain.model.Event
import com.sampleapp.domain.repository.ChatRepository
import org.junit.Test
import org.mockito.Mockito


class EventsConnectUseCaseTest : BaseUseCaseTest(){

    val SUBSCRIBE_EVENTS_TEST = arrayOf(Event.TYPING, Event.STOP_TYPING)

    lateinit var mockChatRepo: ChatRepository
    lateinit var eventsConnectUseCaseTest: EventsConnectUseCase


    override fun setUp() {
        super.setUp()
        mockChatRepo = Mockito.mock(ChatRepository::class.java)
        eventsConnectUseCaseTest = EventsConnectUseCase.mock(mockChatRepo, mockWorkExecutionThread, mockPostExecutionThread)
    }

    @Test
    fun testSubscribeOnEventsUseCase(){
        // ToDo
    }
}