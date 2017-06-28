package com.sampleapp.data.net

import com.github.nkzawa.socketio.client.Socket

interface SocketProvider {

    fun provideSocket(): Socket
}