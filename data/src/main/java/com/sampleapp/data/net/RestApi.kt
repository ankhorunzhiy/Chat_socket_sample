package com.sampleapp.data.net

import com.sampleapp.domain.model.Event
import com.sampleapp.domain.model.Message
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import org.json.JSONObject
import rx.Observable

interface RestApi {

    companion object{
        const val SOCKET_API_URL: String = "https://socketio-chat.now.sh"
    }

    fun addUser(userName: String): Single<String>

    fun onEvents(vararg events: Event) : Flowable<JSONObject>

    fun sendMessage(message: Message): Flowable<Message>

    fun disconnect(): Completable
}