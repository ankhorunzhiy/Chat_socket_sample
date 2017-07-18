package com.sampleapp.di.module

import android.app.Application
import android.content.Context

import com.sampleapp.domain.data.executor.PostExecutionThread
import com.sampleapp.domain.data.executor.ThreadExecutor
import com.sampleapp.data.executor.JobExecutor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.sampleapp.data.repository.chat.ChatDataRepository
import com.sampleapp.data.repository.user.UserDataRepository
import com.sampleapp.domain.data.executor.WorkExecutionThread
import com.sampleapp.domain.data.repository.DataRepositoryImpl
import com.sampleapp.domain.data.repository.DataRepositoryInterface
import com.sampleapp.domain.repository.ChatRepository
import com.sampleapp.domain.repository.UserRepository
import com.sampleapp.util.UIThread
import com.sampleapp.util.WorkThread


import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import rx.subscriptions.CompositeSubscription
import javax.inject.Singleton


@Module
class ApplicationModule(private val application: Application) {


    @Provides
    @Singleton
    internal fun provideThreadExecutor(): ThreadExecutor {
        return JobExecutor()
    }

    @Provides
    @Singleton
    internal fun provideWorkExecutoionThread(): WorkExecutionThread {
        return WorkThread()
    }

    @Provides
    @Singleton
    internal fun providePostExecutionThread(): PostExecutionThread {
        return UIThread()
    }

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun provideUserRepository(userDataRepository: UserDataRepository): UserRepository = userDataRepository

    @Provides
    @Singleton
    fun provideChatRepository(chatDataRepository: ChatDataRepository): ChatRepository = chatDataRepository

    @Provides
    fun compositeDisposable() = CompositeDisposable()

    @Provides
    fun compositeSubscriptions() = CompositeSubscription()


}
