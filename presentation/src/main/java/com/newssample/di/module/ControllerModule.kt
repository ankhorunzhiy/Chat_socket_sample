package com.newssample.di.module

import com.newssample.StartPresenter
import com.newssample.util.ControllerScope
import dagger.Module
import dagger.Provides

/**
 * Created by Anton Khorunzhiy on 5/29/17.
 */
@Module
class ControllerModule {

    @Provides
    @ControllerScope
    fun provideStartPresenter(): StartPresenter = StartPresenter()
}