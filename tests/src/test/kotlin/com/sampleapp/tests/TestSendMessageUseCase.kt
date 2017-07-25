package com.sampleapp.tests

import com.sampleapp.domain.interactor.SendMessageUseCase
import com.sampleapp.domain.model.Event
import com.sampleapp.domain.model.EventModel
import com.sampleapp.domain.model.Message
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
class TestSendMessageUseCase: BaseUseCaseTest() {

    val USER_NAME = "Superman"
    val MESSAGE = "Hello from Ukraine!"


    lateinit var sendMessageUseCase: SendMessageUseCase
    @Mock
    lateinit var mockChatRepo: ChatRepository
    @Spy
    lateinit var testSubscriber: TestSubscriber<EventModel>


    override fun setUp() {
        super.setUp()
        sendMessageUseCase = SendMessageUseCase.mock(mockChatRepo, mockWorkExecutionThread, mockPostExecutionThread)
    }

    @Test
    fun testSendMessage(){
        val message = Message.from(USER_NAME, MESSAGE)
        val resultEvent = getResultEvent()
        Mockito.`when`(mockChatRepo.sendMessage(message)).thenReturn(Flowable.just(resultEvent))
        val params = SendMessageUseCase.Parameters.create(message)
        sendMessageUseCase.execute(testSubscriber, params)
        testSubscriber.assertNoErrors()
        testSubscriber.assertValue(resultEvent)
    }

    private fun getResultEvent(): EventModel {
        val resultEvent = EventModel().apply {
            userName = USER_NAME
            message = MESSAGE
            event = Event.NEW_MESSAGE
        }
        return resultEvent
    }

}