package com.sampleapp.data.net

import com.sampleapp.domain.model.Event
import com.sampleapp.domain.model.Message
import org.json.JSONObject
import rx.Observable

interface RestApi {

    companion object{
        const val SOCKET_API_URL: String = "https://socketio-chat.now.sh"
    }

    fun addUser(userName: String): Observable<String>

    fun onEvents(vararg events: Event) : Observable<JSONObject>

    fun sendMessage(message: Message): Observable<Message>

    fun disconnect(): Observable<Void>
}