package com.sampleapp.data.net

import com.github.nkzawa.emitter.Emitter
import com.github.nkzawa.socketio.client.Socket
import rx.Observable


class RestApiImpl constructor( val socket: Socket): RestApi {

    override fun addUser(userName: String): Observable<String> {
        return Observable.create { subscriber ->
            val loginListener = Emitter.Listener {
                subscriber.onNext(userName)
                subscriber.onCompleted()
                subscriber.unsubscribe()
            }
            socket.on("add user", loginListener)
        }
    }
}