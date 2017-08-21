package com.sampleapp.di.module


import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import rx.subscriptions.CompositeSubscription


@Module
class ApplicationModule {


    @Provides
    fun provideGson(): Gson = GsonBuilder().create()


    @Provides
    fun compositeDisposable() = CompositeDisposable()

    @Provides
    fun compositeSubscriptions() = CompositeSubscription()


}
