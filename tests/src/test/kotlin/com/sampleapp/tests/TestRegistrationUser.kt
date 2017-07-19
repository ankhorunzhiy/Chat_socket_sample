package com.sampleapp.tests

import com.sampleapp.domain.interactor.RegisterUserUseCase
import com.sampleapp.domain.repository.UserRepository
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import rx.Observable
import rx.observers.TestSubscriber

@RunWith(MockitoJUnitRunner::class)
class TestRegistrationUser : BaseUseCaseTest() {

    companion object {
        const val USER_NAME = "Heisenberg"
    }

    @Mock
    lateinit var mockUserRepo: UserRepository
    @Mock
    lateinit var testSubscriber: TestSubscriber<String>

    lateinit var registerUserUseCase: RegisterUserUseCase

    override fun setUp() {
        super.setUp()
        registerUserUseCase = RegisterUserUseCase.mock(mockUserRepo, mockWorkExecutionThread, mockPostExecutionThread)
    }


    @Test
    fun testRegisterUseCaseSuccess() {
        Mockito.`when`(mockUserRepo.addUser(USER_NAME)).thenReturn(Observable.just(USER_NAME))
        val parameters = RegisterUserUseCase.Parameters(USER_NAME)
        registerUserUseCase.execute(testSubscriber, parameters)
        testSubscriber.assertNoErrors()
        testSubscriber.onCompleted()
        testSubscriber.assertReceivedOnNext(arrayListOf(USER_NAME))
    }
}