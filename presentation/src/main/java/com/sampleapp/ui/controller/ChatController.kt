package com.sampleapp.ui.controller

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import com.android.newssample.R
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.sampleapp.data.model.mapper.EventMapper
import com.sampleapp.di.ScreenScope
import com.sampleapp.di.components.ActivityComponent
import com.sampleapp.di.module.RepositoryModule
import com.sampleapp.domain.interactor.DisconnectUseCase
import com.sampleapp.domain.interactor.EventsConnectUseCase
import com.sampleapp.domain.interactor.SendMessageUseCase
import com.sampleapp.domain.model.Event
import com.sampleapp.domain.model.EventModel
import com.sampleapp.navigation.ControllerMediator
import com.sampleapp.rx.SimpleSubscriber
import com.sampleapp.ui.view.ChatControllerView
import com.sampleapp.ui.view.ChatView
import com.sampleapp.util.addTo
import dagger.Provides
import dagger.Subcomponent
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.view_chat.view.*
import javax.inject.Inject
import javax.inject.Named

@Layout(R.layout.screen_chat)
class ChatController(args: Bundle? = null) : BaseController<ChatView, ChatController.Presenter>(args), ChatView {

    @Inject
    lateinit var chatPresenter: Presenter

    @ScreenScope(ChatController::class)
    @Subcomponent(modules = arrayOf(Module::class, RepositoryModule::class))
    interface Component {
        fun inject(loginController: ChatController)

        @Subcomponent.Builder
        interface Builder{
            fun chatModule(module: Module): Builder
            fun build(): Component
        }
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
        component.chatBuilder().chatModule(Module(args)).build().inject(this)
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
        presenter.dispose()
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
                                        val eventMapper: EventMapper,
                                        val compositeDisposable: CompositeDisposable)
        : MvpBasePresenter<ChatView>() {

        fun init() {
            eventsConnectUseCase.execute(
                    SimpleSubscriber<EventModel>({ processEvent(it) }).addTo(compositeDisposable),
                    EventsConnectUseCase.Parameters(
                            arrayOf(Event.NEW_MESSAGE,
                                    Event.USER_JOINED,
                                    Event.USER_LEFT,
                                    Event.TYPING,
                                    Event.STOP_TYPING)))
        }

        fun processEvent(eventModel: EventModel) {
            view.notifyAdapter(eventModel)
        }

        fun sendMessage(text: Editable?) {
            text?.let {
                val textMessage = it.toString()
                if (text.isEmpty()) return@let
                val message = eventMapper.transform(provideUserName(), textMessage)
                sendMessageUseCase.execute(
                        SimpleSubscriber<EventModel>({processEvent(it)}).addTo(compositeDisposable),
                        SendMessageUseCase.Parameters.create(message))
            }
            view.clearMessageText()
        }

        private fun provideUserName(): String? {
            return args?.getString(USER_NAME_KEY)
        }

        fun dispose() {
            if (!compositeDisposable.isDisposed)
                compositeDisposable.dispose()
            eventsConnectUseCase.dispose()
            disconnectUseCase.dispose()
            sendMessageUseCase.dispose()
        }

        fun logout() {
            disconnectUseCase.execute(
                    SimpleSubscriber<Void>({}, {}, {
                        controllerMediator.setRoot(LoginController(), true)}).addTo(compositeDisposable),
                    DisconnectUseCase.Param.INSTANCE)
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
