package com.sampleapp.data.repository.user

import com.sampleapp.data.net.RestApiImpl
import com.sampleapp.data.net.SocketProviderImpl
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserDataStoreFactory @Inject constructor(val socketProvider: SocketProviderImpl) {

    fun createUserStore(): UserDataStore {
        val restApi = RestApiImpl(socketProvider.provideSocket())
        return RemoteUserStore(restApi)
    }
}