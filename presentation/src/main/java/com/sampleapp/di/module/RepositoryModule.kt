package com.sampleapp.di.module

import com.sampleapp.data.repository.chat.ChatDataRepository
import com.sampleapp.data.repository.user.UserDataRepository
import com.sampleapp.domain.repository.ChatRepository
import com.sampleapp.domain.repository.UserRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
interface RepositoryModule {

    @Singleton
    @Binds
    fun provideUserRepository(userDataRepository: UserDataRepository): UserRepository

    @Singleton
    @Binds
    fun provideChatRepository(chatDataRepository: ChatDataRepository): ChatRepository

}