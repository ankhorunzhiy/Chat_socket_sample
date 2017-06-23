package com.sampleapp.controller

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.android.newssample.R
import com.google.gson.Gson
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.hannesdorfmann.mosby3.mvp.MvpView
import com.sampleapp.di.DaggerUtils
import com.sampleapp.di.ScreenScope
import com.sampleapp.di.components.ActivityComponent
import com.sampleapp.di.module.ActivityModule
import com.sampleapp.di.module.NetworkModule
import com.sampleapp.domain.interactor.ApiUseCase
import com.sampleapp.domain.model.ApiAction
import com.sampleapp.util.toHorizontalTransaction
import dagger.Provides
import dagger.Subcomponent
import kotlinx.android.synthetic.main.screen_start.view.*
import javax.inject.Inject
import javax.inject.Named

@Layout(R.layout.screen_start)
class StartController(args: Bundle? = null) : BaseController<MvpView, StartController.Presenter>(args) {

    @Inject
    lateinit var startPresenter: Presenter
    @Inject
    lateinit var controllerMediator: ControllerMediator
    val extra = args

    @ScreenScope(StartController::class)
    @Subcomponent(modules = arrayOf(Module::class))
    interface Component {
        fun inject(startController: StartController)
    }

    @ScreenScope(StartController::class)
    @dagger.Module
    inner class Module {

        @Provides
        @ScreenScope(StartController::class)
        @Named("args")
        fun provideArgs(): Bundle? {
            return extra
        }
    }

    override fun injectToDagger(component: ActivityComponent) {
        component.startComponent(Module()).inject(this)
    }

    override fun createPresenter(): Presenter {
        return startPresenter
    }

    override fun onViewCreated(root: View) {
        root.startText.setOnClickListener {
            getChildRouter(root.nestedLayout)
                    .setPopsLastView(true)
                    .pushController(OverlayController().toHorizontalTransaction())
        }
        root.pagerShowButton.setOnClickListener {
            controllerMediator.push(PagerRootController())
        }
    }

    @ScreenScope(StartController::class)
    class Presenter @Inject constructor(@Named("args") bundle: Bundle?, case: ApiUseCase<ApiAction>) : MvpBasePresenter<MvpView>()

}