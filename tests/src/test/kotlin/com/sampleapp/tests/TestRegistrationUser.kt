package com.sampleapp.tests

import com.sampleapp.domain.interactor.RegisterUserUseCase
import com.sampleapp.domain.repository.UserRepository
import io.reactivex.Flowable
import io.reactivex.Single
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
        Mockito.`when`(mockUserRepo.addUserSingle(USER_NAME)).thenReturn(Single.just(USER_NAME))
        val parameters = RegisterUserUseCase.Parameters(USER_NAME)
        assertSubscriber(testSubscriber, {
            registerUserUseCase.execute(testSubscriber, parameters)
        }, {
            it.assertNoErrors()
            it.onComplete()
            it.assertValue(USER_NAME)
        })


    }
}