package com.sampleapp.data.net

import com.github.nkzawa.socketio.client.IO
import com.github.nkzawa.socketio.client.Socket
import java.net.URISyntaxException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SocketProviderImpl @Inject constructor(): SocketProvider{

    val socket: Socket by lazy {
        try {
            IO.socket(RestApi.SOCKET_API_URL)
        } catch (e: URISyntaxException) {
            throw e
        }
    }

    override fun provideSocket(): Socket = socket

}