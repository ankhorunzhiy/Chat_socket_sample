package com.sampleapp.tests

import com.sampleapp.domain.interactor.DisconnectUseCase
import com.sampleapp.domain.model.EventModel
import com.sampleapp.domain.repository.ChatRepository
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner
import rx.Observable
import rx.observers.TestSubscriber

@RunWith(MockitoJUnitRunner::class)
class TestDisconnectUseCase : BaseUseCaseTest(){

    lateinit var disConnectUseCaseTest: DisconnectUseCase

    @Spy
    lateinit var testSubscriber: TestSubscriber<EventModel>

    @Mock
    lateinit var mockChatRepo: ChatRepository


    override fun setUp() {
        super.setUp()
        disConnectUseCaseTest = DisconnectUseCase.mock(mockChatRepo, mockWorkExecutionThread, mockPostExecutionThread)
    }

    @Test
    fun testDisconnect(){
        Mockito.`when`(mockChatRepo.disconnect()).thenReturn(Observable.empty())
        disConnectUseCaseTest.execute(testSubscriber, null)
        testSubscriber.assertNoErrors()
        testSubscriber.assertCompleted()
    }
}