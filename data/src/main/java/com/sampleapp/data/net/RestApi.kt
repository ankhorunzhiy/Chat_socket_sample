package com.sampleapp.data.net

import rx.Observable

interface RestApi {

    companion object{
        val SOCKET_API_URL: String = "https://socketio-chat.now.sh"
    }

    fun addUser(userName: String): Observable<String>
}