package com.sampleapp.tests

import com.sampleapp.domain.interactor.EventsConnectUseCase
import com.sampleapp.domain.model.Event
import com.sampleapp.domain.model.EventModel
import com.sampleapp.domain.repository.ChatRepository
import io.reactivex.Flowable
import io.reactivex.subscribers.TestSubscriber
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TestEventsConnectUseCase : BaseUseCaseTest(){

    val SUBSCRIBE_EVENTS_TEST = arrayOf(Event.TYPING, Event.STOP_TYPING)
    val USER_NAME = "John Snow"

    lateinit var eventsConnectUseCaseTest: EventsConnectUseCase

    @Spy
    lateinit var testSubscriber: TestSubscriber<EventModel>

    @Mock
    lateinit var mockChatRepo: ChatRepository

    override fun setUp() {
        super.setUp()
        eventsConnectUseCaseTest = EventsConnectUseCase.mock(mockChatRepo, mockWorkExecutionThread, mockPostExecutionThread)
    }

    @Test
    fun testSubscribeOnEventsUseCase(){
        val events = provideResultEvents()
        val resultEventsFlowable = Flowable.fromArray(*events)
        Mockito.`when`(mockChatRepo.on(*SUBSCRIBE_EVENTS_TEST)).thenReturn(resultEventsFlowable)
        eventsConnectUseCaseTest.execute(testSubscriber, EventsConnectUseCase.Parameters(SUBSCRIBE_EVENTS_TEST))
        testSubscriber.assertNoErrors()
        testSubscriber.assertResult(*events)
    }

    private fun provideResultEvents(): Array<EventModel> {
        val typingEvent = EventModel().apply {
            event = Event.TYPING
            userName = USER_NAME
        }

        val stopTypingEvent = EventModel().apply {
            event = Event.STOP_TYPING
            userName = USER_NAME
        }

        return arrayOf(typingEvent, stopTypingEvent)
    }
}