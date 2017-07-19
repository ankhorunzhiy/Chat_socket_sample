package com.sampleapp.tests

import com.sampleapp.domain.data.executor.PostExecutionThread
import com.sampleapp.domain.data.executor.WorkExecutionThread
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito
import rx.schedulers.Schedulers

open class BaseUseCaseTest {

    @Mock
    lateinit var  mockWorkExecutionThread: WorkExecutionThread
    @Mock
    lateinit var mockPostExecutionThread: PostExecutionThread

    @Before
    open fun setUp() {
        Mockito.`when`(mockPostExecutionThread.scheduler).thenReturn(Schedulers.immediate())
        Mockito.`when`(mockWorkExecutionThread.scheduler).thenReturn(Schedulers.immediate())
    }
}