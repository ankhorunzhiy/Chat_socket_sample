package com.sampleapp.tests

import com.sampleapp.domain.data.executor.PostExecutionThread
import com.sampleapp.domain.data.executor.WorkExecutionThread
import com.sampleapp.domain.interactor.RegisterUserUseCase
import com.sampleapp.domain.repository.UserRepository
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner
import rx.Observable
import rx.observers.TestSubscriber
import rx.schedulers.Schedulers


@RunWith(MockitoJUnitRunner::class)
class RegistrationUserTest {

    companion object {
        const val USER_NAME = "Alice"
    }

    lateinit var registerUserUseCase: RegisterUserUseCase

    @Before
    fun setUp() {
        val mockRepository = mock(UserRepository::class.java)
        val mockWorkExecutor = mock(WorkExecutionThread::class.java)
        val mockPostExecutionThread = mock(PostExecutionThread::class.java)
        Mockito.`when`(mockRepository.addUser(USER_NAME)).thenReturn(Observable.just(USER_NAME))
        Mockito.`when`(mockPostExecutionThread.scheduler).thenReturn(Schedulers.immediate())
        Mockito.`when`(mockWorkExecutor.scheduler).thenReturn(Schedulers.immediate())
        registerUserUseCase = RegisterUserUseCase.mock(mockRepository, mockWorkExecutor, mockPostExecutionThread)
    }


    @Test
    fun testRegisterUserUseCaseResults() {
        val subscriber = TestSubscriber.create<String>()
        val parameters = RegisterUserUseCase.Parameters(USER_NAME)
        registerUserUseCase.execute(subscriber, parameters)
        subscriber.assertNoErrors()
        subscriber.onCompleted()
        subscriber.assertReceivedOnNext(arrayListOf(USER_NAME))
    }
}