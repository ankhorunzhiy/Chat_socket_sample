package com.sampleapp.data.net

import com.github.nkzawa.emitter.Emitter
import com.github.nkzawa.socketio.client.Socket
import com.sampleapp.domain.model.Event
import com.sampleapp.domain.model.Message
import io.reactivex.Flowable
import io.reactivex.FlowableEmitter
import org.json.JSONObject
import rx.Observable
import io.reactivex.BackpressureStrategy
import io.reactivex.Completable
import io.reactivex.internal.operators.completable.CompletableEmpty


class RestApiImpl constructor( val socket: Socket): RestApi {

    lateinit var loginListener : Emitter.Listener // ToDO find better way

    override fun addUser(userName: String): Flowable<String> {
        return Flowable.create({ emitter: FlowableEmitter<String> ->
            loginListener = Emitter.Listener {
                emitter.onNext(userName)
                emitter.onComplete()
            }
            socket.connect()
            socket.on(Event.LOGIN.event, loginListener)
            socket.emit(Event.ADD_USER.event, userName)
        }, BackpressureStrategy.BUFFER)
    }

    override fun onEvents(vararg events: Event): Flowable<JSONObject> {
        return Flowable.create({ emitter: FlowableEmitter<JSONObject> ->
            events.forEach { event ->
                socket.on(event.event, {
                    if(!it.isEmpty()){
                        val eventJson = (it.first() as JSONObject).addEvent(event) // Map events to entities
                        emitter.onNext(eventJson)
                    } else {
                        emitter.onNext(JSONObject().addEvent(event))
                    }
                })
                if(!socket.connected())
                    socket.connect()
            }

        }, BackpressureStrategy.BUFFER)
    }

    override fun sendMessage(message: Message): Flowable<Message> {
        return Flowable.fromCallable {
            socket.emit(Event.NEW_MESSAGE.event, message.message)
            message
        }
    }

    override fun disconnect(): Completable {
        socket.disconnect()
        return CompletableEmpty.INSTANCE
    }
}