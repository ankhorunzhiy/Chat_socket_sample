package com.sampleapp.data.model.mapper

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sampleapp.data.model.EventDataModel
import com.sampleapp.domain.model.Event
import com.sampleapp.domain.model.Message
import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EventDataMapper @Inject constructor(val gson: Gson){

    fun map(jsonObject: JSONObject): EventDataModel {
        val userEntityType = object : TypeToken<EventDataModel>() {}.type
        return this.gson.fromJson<EventDataModel>(jsonObject.toString(), userEntityType)
    }

    fun map(message: Message): EventDataModel{
        val eventDataModel = EventDataModel(
                userName = message.userName,
                message = message.message,
                event = Event.NEW_MESSAGE)
        return eventDataModel
    }
}