package com.sampleapp.ui.controller

import android.os.Bundle
import android.text.Editable
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import com.android.newssample.R
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.sampleapp.di.ScreenScope
import com.sampleapp.di.components.ActivityComponent
import com.sampleapp.domain.interactor.DisconnectUseCase
import com.sampleapp.domain.interactor.EventsConnectUseCase
import com.sampleapp.domain.interactor.SendMessageUseCase
import com.sampleapp.domain.model.Event
import com.sampleapp.domain.model.EventModel
import com.sampleapp.domain.model.Message
import com.sampleapp.rx.SimpleSubscriber
import com.sampleapp.ui.view.ChatControllerView
import com.sampleapp.ui.view.ChatView
import dagger.Provides
import dagger.Subcomponent
import kotlinx.android.synthetic.main.view_chat.view.*
import rx.subscriptions.CompositeSubscription
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
    class Module(val args: Bundle?) {
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

    override fun notifyAdapter(eventModel: EventModel) {
        (view as ChatControllerView).notifyAdapter(eventModel)
    }

    override fun clearMessageText() {
        (view as ChatControllerView).clearMessageText()
    }

    override fun onAttach(view: View) {
        super.onAttach(view)
        init(view)
    }

    override fun onDetach(view: View) {
        super.onDetach(view)
        presenter.unsubscribe()
    }

    private fun init(view: View?) {
        setHasOptionsMenu(true)
        chatPresenter.init()
        view?.image_send?.setOnClickListener {
            presenter.sendMessage(view.message_text.text)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.chat_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_left -> presenter.logout()
        }
        return true
    }

    @ScreenScope(ChatController::class)
    class Presenter @Inject constructor(@Named("chatArgs")
                                        val args: Bundle?,
                                        val eventsConnectUseCase: EventsConnectUseCase,
                                        val disconnectUseCase: DisconnectUseCase,
                                        val sendMessageUseCase: SendMessageUseCase,
                                        val controllerMediator: ControllerMediator,
                                        val compositeSubscription: CompositeSubscription)
        : MvpBasePresenter<ChatView>() {

        fun init() {
            val subscriber: SimpleSubscriber<EventModel> = object : SimpleSubscriber<EventModel>() {
                override fun onNext(value: EventModel) {
                    super.onNext(value)
                    processEvent(value)
                }
            }
            eventsConnectUseCase.execute(subscriber, EventsConnectUseCase.Parameters(
                    arrayOf(Event.NEW_MESSAGE,
                            Event.USER_JOINED,
                            Event.USER_LEFT,
                            Event.TYPING,
                            Event.STOP_TYPING)))
            compositeSubscription.add(subscriber)
        }

        fun processEvent(eventModel: EventModel) {
            view.notifyAdapter(eventModel)
        }

        fun sendMessage(text: Editable?) {
            text?.let {
                val textMessage = it.toString()
                if (text.isEmpty()) return@let
                val message = Message.from(provideUserName(), textMessage) // Todo replace in mapping
                sendMessageUseCase.execute(object : SimpleSubscriber<EventModel>() {
                    override fun onNext(value: EventModel) {
                        super.onNext(value)
                        processEvent(value)
                    }
                }, SendMessageUseCase.Parameters.create(message))
            }
            view.clearMessageText()
        }

        private fun provideUserName(): String? {
            return args?.getString(USER_NAME_KEY)
        }

        fun unsubscribe() {
            compositeSubscription.clear()
        }

        fun logout() {
            disconnectUseCase.execute(object : SimpleSubscriber<DisconnectUseCase.Param>(){
                override fun onCompleted() {
                    controllerMediator.setRoot(LoginController(), true)
                }
            }, DisconnectUseCase.Param.INSTANCE)
        }
    }

    companion object {
        const val USER_NAME_KEY = "user_name_key"

        fun newArgs(userName: String): Bundle {
            val args = Bundle()
            args.putString(USER_NAME_KEY, userName)
            return args
        }
    }

}
