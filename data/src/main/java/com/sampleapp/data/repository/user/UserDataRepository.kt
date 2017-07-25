package com.sampleapp.data.repository.user

import com.sampleapp.domain.repository.UserRepository
import io.reactivex.Flowable
import rx.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserDataRepository @Inject constructor(val userDataStoreFactory: UserDataStoreFactory): UserRepository {

    override fun addUser(userName: String): Flowable<String> {
        return userDataStoreFactory.createUserStore().addUser(userName)
    }
}