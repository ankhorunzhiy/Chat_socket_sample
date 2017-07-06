package com.sampleapp.data.net

import com.sampleapp.data.model.mapper.EventDataMapper
import org.json.JSONObject
import rx.Observable

interface RestApi {

    companion object{
        val SOCKET_API_URL: String = "https://socketio-chat.now.sh"
    }

    fun addUser(userName: String): Observable<String>

    fun onEvents(events: Array<out String>) : Observable<JSONObject>
}