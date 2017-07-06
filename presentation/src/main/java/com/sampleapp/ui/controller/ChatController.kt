package com.sampleapp.ui.controller

import android.os.Bundle
import android.util.Log
import android.view.View
import com.android.newssample.R
import com.github.nkzawa.socketio.client.Socket
import com.github.nkzawa.socketio.client.Socket.EVENT_CONNECT
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.sampleapp.di.ScreenScope
import com.sampleapp.di.components.ActivityComponent
import com.sampleapp.domain.interactor.EventsConnectUseCase
import com.sampleapp.domain.model.EventModel
import com.sampleapp.rx.SimpleSubscriber
import com.sampleapp.ui.view.ChatView
import dagger.Provides
import dagger.Subcomponent
import javax.inject.Inject
import javax.inject.Named

@Layout(R.layout.screen_chat)
class ChatController(args: Bundle? = null) : BaseController<ChatView, ChatController.Presenter>(args), ChatView {

    @Inject
    lateinit var chatPresenter: Presenter

    @ScreenScope(ChatController::class)
    @Subcomponent(modules = arrayOf(Module::class))
    interface Component {
        fun inject(loginController: ChatController)
    }

    @dagger.Module
    class Module (val args: Bundle?){
        @Provides
        @Named("chatArgs")
        fun provideArgs(): Bundle? {
            return args
        }
    }

    override fun createPresenter(): Presenter {
        return chatPresenter
    }

    override fun injectToDagger(component: ActivityComponent) {
        component.chatComponent(Module(args)).inject(this)
    }

    override fun onViewCreated(root: View) {
        super.onViewCreated(root)
        setTitle(R.string.chat)
    }


    override fun onAttach(view: View) {
        super.onAttach(view)
        chatPresenter.init()
    }

    @ScreenScope(ChatController::class)
    class Presenter @Inject constructor(@Named("chatArgs")
                                        val args: Bundle?,
                                        val eventsConnectUseCase: EventsConnectUseCase)
        : MvpBasePresenter<ChatView>(){

        fun init() {
           view.showProgress()
           eventsConnectUseCase.execute(object : SimpleSubscriber<EventModel>() {
               override fun onNext(value: EventModel) {
                   super.onNext(value)
                   view.hideProgress()

               }
           }, EventsConnectUseCase.Parameters(arrayOf(
                   EVENT_CONNECT,
                   "new message")))} // ToDo rework
    }

    companion object{
        const val USER_NAME_KEY = "user_name_key"

        fun newArgs(userName: String): Bundle{
            val args = Bundle()
            args.putString(USER_NAME_KEY, userName)
            return args
        }
    }

}
