package com.sampleapp.tests

import com.sampleapp.domain.interactor.RegisterUserUseCase
import com.sampleapp.domain.repository.UserRepository
import io.reactivex.Flowable
import io.reactivex.subscribers.TestSubscriber
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TestRegistrationUser : BaseUseCaseTest() {

    companion object {
        const val USER_NAME = "Heisenberg"
    }

    @Mock
    lateinit var mockUserRepo: UserRepository
    @Spy
    lateinit var testSubscriber: TestSubscriber<String>

    lateinit var registerUserUseCase: RegisterUserUseCase

    override fun setUp() {
        super.setUp()
        registerUserUseCase = RegisterUserUseCase.mock(mockUserRepo, mockWorkExecutionThread, mockPostExecutionThread)
    }


    @Test
    fun testRegisterUseCaseSuccess() {
        Mockito.`when`(mockUserRepo.addUser(USER_NAME)).thenReturn(Flowable.just(USER_NAME))
        val parameters = RegisterUserUseCase.Parameters(USER_NAME)
        registerUserUseCase.execute(testSubscriber, parameters)
        testSubscriber.assertNoErrors()
        testSubscriber.onComplete()
        testSubscriber.assertValue(USER_NAME)
    }
}