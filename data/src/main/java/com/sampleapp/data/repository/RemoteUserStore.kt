package com.sampleapp.data.repository

import com.sampleapp.data.net.RestApi
import rx.Observable


class RemoteUserStore constructor(val restApi: RestApi): UserDataStore {

    override fun addUser(userName: String): Observable<String> {
        return restApi.addUser(userName)
    }
}