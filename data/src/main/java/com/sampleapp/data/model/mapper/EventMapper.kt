package com.sampleapp.data.model.mapper

import com.sampleapp.data.model.EventDataModel
import com.sampleapp.domain.model.EventModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EventMapper @Inject constructor(){

    fun transform(eventDataModel: EventDataModel): EventModel{
        val eventModel = EventModel()
        eventModel.message = eventDataModel.message
        eventModel.userName = eventDataModel.userName
        eventModel.numUsers = eventDataModel.numUsers
        eventModel.event = eventDataModel.event
        return eventModel
    }

}