package com.sampleapp.tests

import com.sampleapp.data.model.EventDataModel
import com.sampleapp.data.model.mapper.EventMapper
import com.sampleapp.domain.model.Event
import com.sampleapp.domain.model.Message
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TestMapping {

    val USER_NAME = "David Beckham"
    val MESSAGE = "Hello World"
    val NUM_USERS = 7
    val EVENT = Event.NEW_MESSAGE

    @Spy
    lateinit var mapper: EventMapper

    @Test
    fun testDataModel2Domain() {
        val eventDataModel = EventDataModel(USER_NAME, MESSAGE, NUM_USERS, EVENT)
        val eventDomainModel = mapper.transform(eventDataModel)
        assertThat(eventDataModel.userName).isEqualTo(eventDomainModel.userName)
        assertThat(eventDataModel.message).isEqualTo(eventDomainModel.message)
        assertThat(eventDataModel.numUsers).isEqualTo(eventDomainModel.numUsers)
        assertThat(eventDataModel.event).isEqualTo(eventDomainModel.event)
    }

    @Test
    fun testMessageToEventDataModel() {
        val message = Message.from(USER_NAME, MESSAGE)
        assertThat(message.userName).isEqualTo(USER_NAME)
        assertThat(message.message).isEqualTo(MESSAGE)
    }
}