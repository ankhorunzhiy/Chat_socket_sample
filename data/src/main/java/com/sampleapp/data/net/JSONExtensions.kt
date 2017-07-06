package com.sampleapp.data.net

import com.sampleapp.domain.model.Event
import org.json.JSONObject

fun JSONObject.addEvent(event: Event): JSONObject{
    put("event", event)
    return this
}