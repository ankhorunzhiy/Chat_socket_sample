package com.sampleapp.data.net

import com.github.nkzawa.emitter.Emitter
import com.github.nkzawa.socketio.client.Socket
import com.sampleapp.data.model.mapper.EventDataMapper
import org.json.JSONObject
import rx.Observable


class RestApiImpl constructor( val socket: Socket): RestApi {

    lateinit var loginListener : Emitter.Listener // ToDO find better way

    override fun addUser(userName: String): Observable<String> {
        return Observable.create { subscriber ->
            loginListener = Emitter.Listener {
                subscriber.onNext(userName)
                subscriber.onCompleted()
                subscriber.unsubscribe()
                socket.off("login", loginListener)
                socket.disconnect()
            }
            socket.connect()
            socket.on("login", loginListener)   // ToDo replace commands with enums
            socket.emit("add user", userName)
        }
    }

    override fun onEvents(events: Array<out String>): Observable<JSONObject> {
        return Observable.create { subscriber->
            events.forEach {
                socket.on(it, { subscriber.onNext(it.first() as JSONObject)})
                socket.connect()
            }
        }
    }

}