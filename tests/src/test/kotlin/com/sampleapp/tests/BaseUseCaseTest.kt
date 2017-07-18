package com.sampleapp.tests

import com.sampleapp.domain.data.executor.PostExecutionThread
import com.sampleapp.domain.data.executor.WorkExecutionThread
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import rx.schedulers.Schedulers

@RunWith(MockitoJUnitRunner::class)
open class BaseUseCaseTest {

    lateinit var  mockWorkExecutionThread: WorkExecutionThread
    lateinit var mockPostExecutionThread: PostExecutionThread

    @Before
    open fun setUp() {
        mockWorkExecutionThread = Mockito.mock(WorkExecutionThread::class.java)
        mockPostExecutionThread = Mockito.mock(PostExecutionThread::class.java)
        Mockito.`when`(mockPostExecutionThread.scheduler).thenReturn(Schedulers.immediate())
        Mockito.`when`(mockWorkExecutionThread.scheduler).thenReturn(Schedulers.immediate())
    }
}