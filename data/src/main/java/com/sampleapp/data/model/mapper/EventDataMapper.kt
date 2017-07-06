package com.sampleapp.data.model.mapper

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sampleapp.data.model.EventDataModel
import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EventDataMapper @Inject constructor(val gson: Gson){

    fun map(jsonObject: JSONObject): EventDataModel {
        val userEntityType = object : TypeToken<EventDataModel>() {}.type
        return this.gson.fromJson<EventDataModel>(jsonObject.toString(), userEntityType)
    }
}