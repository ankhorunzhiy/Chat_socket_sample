package com.sampleapp.tests

import com.sampleapp.data.executor.JobExecutor
import com.sampleapp.data.net.SocketProviderImpl
import com.sampleapp.data.repository.UserDataRepository
import com.sampleapp.data.repository.UserDataStoreFactory
import com.sampleapp.domain.data.executor.PostExecutionThread
import com.sampleapp.domain.data.executor.ThreadExecutor
import com.sampleapp.domain.interactor.RegisterUserUseCase
import com.sampleapp.domain.repository.UserRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.runners.MockitoJUnitRunner
import rx.android.schedulers.AndroidSchedulers
import rx.observers.TestSubscriber


@RunWith(MockitoJUnitRunner::class)
class RegistrationUserTest {

    lateinit var registerUserUseCase: RegisterUserUseCase

    @Before
    fun setUp() {
        val mockRepository = mock(UserRepository::class.java)
        val mockThreadExecutor = mock(ThreadExecutor::class.java)
        val mockPostExecutionThread = mock(PostExecutionThread::class.java)
        Mockito.`when`(mockRepository).thenReturn(UserDataRepository(UserDataStoreFactory(SocketProviderImpl())))
        Mockito.`when`(mockThreadExecutor).thenReturn(JobExecutor())
        Mockito.`when`(mockPostExecutionThread).thenReturn(PostExecutionThread { AndroidSchedulers.mainThread() })
        registerUserUseCase = RegisterUserUseCase.mock(mockRepository, mockThreadExecutor, mockPostExecutionThread)
    }


    @Test
    fun testRegisterUserUseCaseResults() {
        val subscriber = TestSubscriber.create<String>()
        registerUserUseCase.execute(subscriber, RegisterUserUseCase.Parameters("Alice"))
        subscriber.assertNoErrors()
        subscriber.assertCompleted()
        assertThat(subscriber.onNextEvents[0]).isEqualTo("Alice")
    }
}