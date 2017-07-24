package com.sampleapp.data.net

import com.github.nkzawa.emitter.Emitter
import com.github.nkzawa.socketio.client.Socket
import com.sampleapp.domain.model.Event
import com.sampleapp.domain.model.Message
import org.json.JSONObject
import rx.Observable


class RestApiImpl constructor( val socket: Socket): RestApi {

    lateinit var loginListener : Emitter.Listener // ToDO find better way

    override fun addUser(userName: String): Observable<String> {
        return Observable.create { subscriber ->
            loginListener = Emitter.Listener {
                subscriber.onNext(userName)
                subscriber.onCompleted()
            }
            socket.connect()
            socket.on(Event.LOGIN.event, loginListener)
            socket.emit(Event.ADD_USER.event, userName)
        }
    }

    override fun onEvents(vararg events: Event): Observable<JSONObject> {
        return Observable.create { subscriber->
            events.forEach { event ->
                socket.on(event.event, {
                    if(!it.isEmpty()){
                        val eventJson = (it.first() as JSONObject).addEvent(event) // Map events to entities
                        subscriber.onNext(eventJson)
                    } else {
                        subscriber.onNext(JSONObject().addEvent(event))
                    }
                })
                if(!socket.connected())
                    socket.connect()
            }
        }
    }

    override fun sendMessage(message: Message): Observable<Message> {
        return Observable.fromCallable {
            socket.emit(Event.NEW_MESSAGE.event, message.message)
            message
        }
    }

    override fun disconnect(): Observable<Void> {
        socket.disconnect()
        return Observable.empty()
    }
}