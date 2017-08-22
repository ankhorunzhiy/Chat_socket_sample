package com.sampleapp.tests

import com.sampleapp.domain.data.executor.PostExecutionThread
import com.sampleapp.domain.data.executor.WorkExecutionThread
import io.reactivex.schedulers.Schedulers
import io.reactivex.subscribers.TestSubscriber
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito

open class BaseUseCaseTest {

    @Mock
    lateinit var  mockWorkExecutionThread: WorkExecutionThread
    @Mock
    lateinit var mockPostExecutionThread: PostExecutionThread

    @Before
    open fun setUp() {
        Mockito.`when`(mockPostExecutionThread.scheduler).thenReturn(Schedulers.trampoline())
        Mockito.`when`(mockWorkExecutionThread.scheduler).thenReturn(Schedulers.trampoline())
    }

    inline fun <T> assertSubscriber (subscriber: TestSubscriber<T>, execute: () -> Unit,
                                     assert: (subscriber: TestSubscriber<T>)-> Unit) {
        execute.invoke()
        assert.invoke(subscriber)
    }
}