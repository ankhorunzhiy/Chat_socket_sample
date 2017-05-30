package com.newssample.di.module

import android.content.Context
import android.view.LayoutInflater
import com.newssample.util.ActivityScope
import dagger.Module
import dagger.Provides

@Module
class ActivityModule {

    @Provides
    @ActivityScope
    fun provideLayoutInflater(context: Context): LayoutInflater {
        return LayoutInflater.from(context)
    }
}
