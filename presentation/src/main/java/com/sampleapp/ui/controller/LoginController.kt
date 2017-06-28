package com.sampleapp.ui.controller

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import com.android.newssample.R
import com.google.gson.Gson
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.hannesdorfmann.mosby3.mvp.MvpView
import com.jakewharton.rxbinding2.widget.RxTextView
import com.sampleapp.di.DaggerUtils
import com.sampleapp.di.ScreenScope
import com.sampleapp.di.components.ActivityComponent
import com.sampleapp.di.module.ActivityModule
import com.sampleapp.di.module.NetworkModule
import com.sampleapp.domain.interactor.RegisterUserUseCase
import com.sampleapp.domain.model.ApiAction
import com.sampleapp.rx.SimpleSubscriber
import com.sampleapp.ui.view.LoginView
import com.sampleapp.util.toHorizontalTransaction
import com.sampleapp.util.visible
import dagger.Provides
import dagger.Subcomponent
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.screen_login.view.*
import kotlinx.android.synthetic.main.view_login.view.*
import rx.Subscriber
import javax.inject.Inject
import javax.inject.Named

@Layout(R.layout.screen_login)
class LoginController(args: Bundle? = null) : BaseController<LoginView, LoginController.Presenter>(args), LoginView {

    @Inject
    lateinit var startPresenter: Presenter
    @Inject
    lateinit var controllerMediator: ControllerMediator
    @Inject
    lateinit var compositeDisposable: CompositeDisposable

    @ScreenScope(LoginController::class)
    @Subcomponent()
    interface Component {
        fun inject(loginController: LoginController)
    }

    override fun injectToDagger(component: ActivityComponent) {
        component.startComponent().inject(this)
    }

    override fun createPresenter(): Presenter {
        return startPresenter
    }

    override fun onAttach(view: View) {
        super.onAttach(view)
        compositeDisposable.add(RxTextView.afterTextChangeEvents(view.user_nick).subscribe {
            getPresenter().onNickTextChange(it.editable()?.toString())
        })
        view.login.setOnClickListener {
            getPresenter().onLogin(view.user_nick.text?.toString())
        }
    }

    override fun onDestroyView(view: View) {
        super.onDestroyView(view)
        compositeDisposable.clear()
    }

    override fun showLoginButton(isShow: Boolean) {
        view?.login?.visible(isShow)
    }

    @ScreenScope(LoginController::class)
    class Presenter @Inject constructor(val registerUserUseCase: RegisterUserUseCase,
                                        val controllerMediator: ControllerMediator)
                                        : MvpBasePresenter<LoginView>() {

        fun onNickTextChange(textNick: String?) {
            view.showLoginButton(!TextUtils.isEmpty(textNick))
        }

        fun  onLogin(nick: String?) {
            if(nick != null)
                view.showProgress()
                registerUserUseCase.execute(object : SimpleSubscriber<String>() {
                    override fun onNext(value: String) {
                        controllerMediator.push(PagerRootController())
                        view.hideProgress()
                    }

                    override fun onError(e: Throwable?) {
                        super.onError(e)        // ToDo handle error
                        view.showProgress()
                    }
                }, RegisterUserUseCase.Parameters(nick))
        }
    }

}