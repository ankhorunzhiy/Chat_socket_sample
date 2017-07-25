package com.sampleapp.tests

import com.sampleapp.domain.interactor.DisconnectUseCase
import com.sampleapp.domain.repository.ChatRepository
import io.reactivex.internal.operators.completable.CompletableEmpty
import io.reactivex.subscribers.TestSubscriber
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TestDisconnectUseCase : BaseUseCaseTest(){

    lateinit var disConnectUseCase: DisconnectUseCase

    @Spy
    lateinit var testSubscriber: TestSubscriber<Void>

    @Mock
    lateinit var mockChatRepo: ChatRepository


    override fun setUp() {
        super.setUp()
        disConnectUseCase = DisconnectUseCase.mock(mockChatRepo, mockWorkExecutionThread, mockPostExecutionThread)
    }

    @Test
    fun testDisconnect(){
        Mockito.`when`(mockChatRepo.disconnect()).thenReturn(CompletableEmpty.INSTANCE)
        disConnectUseCase.execute(testSubscriber, DisconnectUseCase.Param.INSTANCE)
        testSubscriber.assertNoErrors()
        testSubscriber.assertComplete()
    }
}