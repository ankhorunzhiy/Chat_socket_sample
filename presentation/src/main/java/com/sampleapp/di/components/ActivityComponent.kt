package com.sampleapp.di.components

import com.bluelinelabs.conductor.Router
import com.google.gson.Gson
import com.sampleapp.MainActivity
import com.sampleapp.controller.ControllerMediator
import com.sampleapp.di.ActivityScope
import com.sampleapp.di.module.ActivityModule
import com.sampleapp.domain.data.executor.PostExecutionThread
import com.sampleapp.domain.data.executor.ThreadExecutor
import com.sampleapp.domain.data.repository.DataRepositoryInterface

@ActivityScope
@dagger.Subcomponent(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {
    fun inject(mainActivity: MainActivity)
    fun threadExecutor(): ThreadExecutor
    fun postExecutionThread(): PostExecutionThread
    fun gson(): Gson
    fun dataRepository(): DataRepositoryInterface
    fun router(): Router
    fun controllerMediator(): ControllerMediator
}