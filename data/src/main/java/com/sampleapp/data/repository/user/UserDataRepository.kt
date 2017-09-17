package com.sampleapp.data.repository.user

import com.sampleapp.domain.repository.UserRepository
import io.reactivex.Flowable
import io.reactivex.Single
import rx.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserDataRepository @Inject constructor(val userDataStoreFactory: UserDataStoreFactory): UserRepository {

    var flowable: Single<String>? = null
    override fun addUser(userName: String): Single<String> {
        if(flowable == null)
            flowable = userDataStoreFactory.createUserStore().addUser(userName).cache()
        return flowable as Single<String>
    }
}