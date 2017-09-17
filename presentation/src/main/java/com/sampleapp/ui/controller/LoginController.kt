package com.sampleapp.ui.controller

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import com.android.newssample.R
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.jakewharton.rxbinding2.widget.RxTextView
import com.sampleapp.di.ScreenScope
import com.sampleapp.di.components.ActivityComponent
import com.sampleapp.di.module.RepositoryModule
import com.sampleapp.domain.interactor.RegisterUserUseCase
import com.sampleapp.navigation.ControllerMediator
import com.sampleapp.rx.SimpleSubscriber
import com.sampleapp.ui.view.LoginView
import com.sampleapp.util.addTo
import com.sampleapp.util.hideKeybord
import com.sampleapp.util.visible
import dagger.Subcomponent
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.view_login.view.*
import javax.inject.Inject


@Layout(R.layout.screen_login)
class LoginController(args: Bundle? = null) : BaseController<LoginView, LoginController.Presenter>(args), LoginView {

    @Inject
    lateinit var startPresenter: Presenter
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

    override fun onViewCreated(root: View) {
        super.onViewCreated(root)
        setTitle(R.string.login)
    }

    override fun onAttach(view: View) {
        super.onAttach(view)
        compositeDisposable.add(RxTextView.afterTextChangeEvents(view.user_nick).subscribe {
            getPresenter().onNickTextChange(it.editable()?.toString())
        })
        view.login.setOnClickListener { login() }
        view.user_nick.setOnEditorActionListener(TextView.OnEditorActionListener { _, id, _ ->
            if (id == R.id.user_nick || id == EditorInfo.IME_NULL) {
                login()
                return@OnEditorActionListener true
            }
            false
        })
    }

    fun login() {
        presenter.onLogin(view?.user_nick?.text?.toString())
        view?.user_nick?.hideKeybord()
    }

    override fun onDetach(view: View) {
        super.onDetach(view)
        if (!compositeDisposable.isDisposed)
            compositeDisposable.dispose()
        presenter.dispose()
    }

    override fun showLoginButton(isShow: Boolean) {
        view?.login?.visible(isShow)
    }

    @ScreenScope(LoginController::class)
    class Presenter @Inject constructor(val registerUserUseCase: RegisterUserUseCase,
                                        val controllerMediator: ControllerMediator,
                                        val compositeDisposable: CompositeDisposable)
        : MvpBasePresenter<LoginView>() {


        fun subscriber() = SimpleSubscriber<String> ({
            view.hideProgress()
            controllerMediator.setRoot(ChatController(ChatController.newArgs(it)), true)
        })

        fun onNickTextChange(textNick: String?) {
            view.showLoginButton(!TextUtils.isEmpty(textNick))
        }

        override fun attachView(view: LoginView?) {
            super.attachView(view)
            registerUserUseCase.subscribe(subscriber())
        }

        fun onLogin(nick: String?) {
            if (nick != null)
                view.showProgress()
            registerUserUseCase.execute(
                    subscriber().addTo(compositeDisposable),
                    RegisterUserUseCase.Parameters(nick))
        }

        fun dispose() {
            registerUserUseCase.dispose()
            compositeDisposable.dispose()
        }
    }

}