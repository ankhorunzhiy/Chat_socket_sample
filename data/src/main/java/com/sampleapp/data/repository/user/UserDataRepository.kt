package com.sampleapp.data.repository.user

import com.sampleapp.domain.repository.UserRepository
import rx.Observable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserDataRepository @Inject constructor(val userDataStoreFactory: UserDataStoreFactory): UserRepository {

    override fun addUser(userName: String): Observable<String> {
        return userDataStoreFactory.createUserStore().addUser(userName)
    }
}