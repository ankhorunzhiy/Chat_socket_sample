package com.sampleapp.data.repository.user

import com.sampleapp.data.cache.FlowableCacher
import com.sampleapp.domain.repository.UserRepository
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserDataRepository @Inject constructor(val userDataStoreFactory: UserDataStoreFactory,
                                             val flowableCacher: FlowableCacher): UserRepository {

    companion object{
        @JvmStatic val ADD_USER: String = "add_user_key"
    }

    override fun addUserSingle(userName: String?, useCache: Boolean): Single<String>? {
        val addUserFlowable = userDataStoreFactory.createUserStore().addUser(userName)
        if(!useCache){
            return addUserFlowable
        } else
            return provideOrPut(addUserFlowable)
    }

    private fun provideOrPut(addUserFlowable: Single<String>): Single<String>? {
        if (flowableCacher.contains(ADD_USER))
            return flowableCacher.get(ADD_USER).firstOrError() as Single<String>
        val cached = addUserFlowable.cache()
        flowableCacher.put(ADD_USER, cached.toFlowable())
        return cached
    }

    override fun provideCached(): Single<String>? {
        return flowableCacher.get(ADD_USER).firstOrError() as Single<String>
    }

    override fun clearCachedUser() {
        flowableCacher.clear(ADD_USER)
    }
}