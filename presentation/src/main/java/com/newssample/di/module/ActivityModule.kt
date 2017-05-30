package com.newssample.di.module

import android.content.Context
import android.view.LayoutInflater

import dagger.Module
import dagger.Provides

@Module
class ActivityModule {

    @Provides
    fun provideLayoutInflater(context: Context): LayoutInflater {
        return LayoutInflater.from(context)
    }
}
