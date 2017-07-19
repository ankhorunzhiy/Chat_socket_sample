package com.sampleapp.tests

import com.google.gson.Gson
import com.sampleapp.data.model.mapper.EventDataMapper
import org.assertj.core.api.Assertions.assertThat
import org.json.JSONObject
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TestParsing {

    val USER_NAME = "Mister Po"
    val MESSAGE = "Text for test"
    val NUM_USERS = 34

    val testJsonString = "{ \"username\": \"$USER_NAME\", " +
            "\"message\": \"$MESSAGE\", " +
            "\"numUsers\": $NUM_USERS }"

    @Spy
    lateinit var gson: Gson
    @Mock
    lateinit var testJson: JSONObject

    @InjectMocks
    lateinit var dataMapper: EventDataMapper


    @Test
    fun testParseEvent() {
        Mockito.`when`(testJson.toString()).thenReturn(testJsonString)
        val eventDataModel = dataMapper.map(testJson)
        assertThat(eventDataModel).isNotNull()
        assertThat(eventDataModel.userName).isEqualTo(USER_NAME)
        assertThat(eventDataModel.message).isEqualTo(MESSAGE)
        assertThat(eventDataModel.numUsers).isEqualTo(NUM_USERS)
    }
}