package com.sampleapp.tests

import com.sampleapp.domain.interactor.RegisterUserUseCase
import com.sampleapp.domain.repository.UserRepository
import org.junit.Test
import org.mockito.Mockito
import rx.Observable
import rx.observers.TestSubscriber


class RegistrationUserTest : BaseUseCaseTest() {

    companion object {
        const val USER_NAME = "Alice"
    }

    lateinit var mockUserRepo: UserRepository
    lateinit var registerUserUseCase: RegisterUserUseCase

    override fun setUp() {
        super.setUp()
        mockUserRepo = Mockito.mock(UserRepository::class.java)
        registerUserUseCase = RegisterUserUseCase.mock(mockUserRepo, mockWorkExecutionThread, mockPostExecutionThread)
    }


    @Test
    fun testRegisterUseCaseSuccess() {
        Mockito.`when`(mockUserRepo.addUser(USER_NAME)).thenReturn(Observable.just(USER_NAME))
        val subscriber = TestSubscriber.create<String>()
        val parameters = RegisterUserUseCase.Parameters(USER_NAME)
        registerUserUseCase.execute(subscriber, parameters)
        subscriber.assertNoErrors()
        subscriber.onCompleted()
        subscriber.assertReceivedOnNext(arrayListOf(USER_NAME))
    }
}