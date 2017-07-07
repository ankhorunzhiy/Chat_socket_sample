package com.sampleapp.ui.view

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.android.newssample.R
import com.sampleapp.domain.model.Event
import com.sampleapp.domain.model.EventModel
import kotlinx.android.synthetic.main.item_message.view.*
import kotlinx.android.synthetic.main.item_user.view.*
import kotlinx.android.synthetic.main.view_chat.view.*


class ChatControllerView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

    var adapter: Adapter
    init {
        View.inflate(context, R.layout.view_chat, this)
        adapter = Adapter()
        chat_recycler_view.adapter = adapter
    }

    fun notifyAdapter(eventModel: EventModel){
        val event = eventModel.event
        if(event == null || event == Event.CONNECT || event == Event.DISCONNECT) return
//        adapter.events.add(eventModel) // ToDo add notifyadapter
    }

    class Adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>(){

        lateinit var context: Context

        private enum class Type{
            MESSAGE, USER_STATUS, TYPING
        }

        var events: MutableList<EventModel> = ArrayList()

        override fun getItemCount(): Int = events.size

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
            (holder as BaseHolder).bind(events[position])
        }

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
            val inflater = LayoutInflater.from(context)
            when(viewType){
                Type.MESSAGE.ordinal -> return MessageHolder(inflater.inflate(R.layout.item_message, null))
                Type.USER_STATUS.ordinal -> return UserStatusHolder(inflater.inflate(R.layout.item_user, null))
                Type.TYPING.ordinal -> return TypingHolder(inflater.inflate(R.layout.item_user, null))
            }
            throw IllegalArgumentException("Unknown view type")
        }

        override fun getItemViewType(position: Int): Int {
            when(events[position].event){
                Event.NEW_MESSAGE -> return Type.MESSAGE.ordinal
                Event.USER_JOINED, Event.USER_LEFT -> Type.USER_STATUS
                Event.TYPING, Event.STOP_TYPING -> Type.TYPING
            }
            throw IllegalArgumentException("Unknown type")
        }

        override fun onAttachedToRecyclerView(recyclerView: RecyclerView?) {
            super.onAttachedToRecyclerView(recyclerView)
            recyclerView?.let { this.context = context }
        }

        class MessageHolder(view: View) : BaseHolder(view){

            override fun bind(eventModel: EventModel){
                itemView.user_name.text = eventModel.userName
                itemView.message.text = eventModel.message
            }

        }

        class TypingHolder(view: View): BaseHolder(view){

            override fun bind(eventModel: EventModel){
                val typingText = itemView.resources.getString(R.string.is_typing, eventModel.userName)
                itemView.user.text = typingText
            }

        }

        class UserStatusHolder(view: View): BaseHolder(view){

            override fun bind(eventModel: EventModel){
                val event = eventModel.event
                val stringId = if(event == Event.USER_JOINED) R.string.has_joined else R.string.has_left
                itemView.user.text = itemView.resources.getText(stringId, eventModel.userName)
            }

        }

        abstract class BaseHolder(view: View) : RecyclerView.ViewHolder(view){
            abstract fun bind(eventModel: EventModel)
        }
    }
}