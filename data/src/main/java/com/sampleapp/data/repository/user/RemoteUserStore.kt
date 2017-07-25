package com.sampleapp.data.repository.user

import com.sampleapp.data.net.RestApi
import io.reactivex.Flowable
import io.reactivex.Single
import rx.Observable


class RemoteUserStore constructor(val restApi: RestApi): UserDataStore {

    override fun addUser(userName: String): Single<String> {
        return restApi.addUser(userName)
    }
}