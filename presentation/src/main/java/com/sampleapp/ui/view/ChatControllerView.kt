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
import kotlinx.android.synthetic.main.item_user_action.view.*
import kotlinx.android.synthetic.main.view_chat.view.*


class ChatControllerView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr) {

    var adapter: Adapter
    init {
        View.inflate(context, R.layout.view_chat, this)
        adapter = Adapter(context)
        chat_recycler_view.adapter = adapter
    }

    fun notifyAdapter(eventModel: EventModel){
        if(eventModel.event == null) return
        adapter.handleEvent(eventModel)
    }

    class Adapter(val context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>(){

        var events: MutableList<EventModel> = ArrayList()
        lateinit var recyclerView: RecyclerView

        override fun getItemCount(): Int = events.size

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
            (holder as BaseHolder).bind(events[position])
        }

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
            val inflater = LayoutInflater.from(context)
            when(viewType){
                Event.NEW_MESSAGE.ordinal -> return MessageHolder(inflater.inflate(R.layout.item_message, null))
                Event.USER_JOINED.ordinal, Event.USER_LEFT.ordinal ->
                    return UserStatusHolder(inflater.inflate(R.layout.item_user_action, null))
                Event.TYPING.ordinal -> return TypingHolder(inflater.inflate(R.layout.item_user, null))
            }
            throw IllegalArgumentException("Unknown view type")
        }

        override fun getItemViewType(position: Int): Int {
            return events[position].event.ordinal
        }

        override fun onAttachedToRecyclerView(recyclerView: RecyclerView?) {
            super.onAttachedToRecyclerView(recyclerView)
            this.recyclerView = recyclerView as RecyclerView
        }

        class MessageHolder(view: View) : BaseHolder(view){

            override fun bind(eventModel: EventModel){
                itemView.user_name.text = eventModel.userName.plus(":")
                itemView.message.text = eventModel.message
            }

        }

        class TypingHolder(view: View): BaseHolder(view){

            override fun bind(eventModel: EventModel){
                val typingText = itemView.resources.getString(R.string.is_typing)
                itemView.details.text = typingText
                itemView.user.text = eventModel.userName
            }

        }

        class UserStatusHolder(view: View): BaseHolder(view){

            override fun bind(eventModel: EventModel){
                val event = eventModel.event
                val stringId = if(event == Event.USER_JOINED) R.string.has_joined else R.string.has_left
                itemView.user_action.text = itemView.resources.getString(stringId, eventModel.userName)
            }

        }

        abstract class BaseHolder(view: View) : RecyclerView.ViewHolder(view){
            abstract fun bind(eventModel: EventModel)
        }

        fun handleEvent(eventModel: EventModel) {
            if (eventModel.event == Event.STOP_TYPING)
                handleStopTyping(eventModel)
            else{
                val size = events.size
                events.add(eventModel)
                notifyItemInserted(size)
                recyclerView.smoothScrollToPosition(events.size)
            }

        }

        fun handleStopTyping(eventModel: EventModel) {
            val listIterator = events.listIterator(events.size)
            while (listIterator.hasPrevious()) {
                val previous = listIterator.previous()
                if(previous.userName == eventModel.userName && previous.event == Event.TYPING){
                    listIterator.remove()
                    val previousIndex = listIterator.previousIndex()
                    notifyItemRemoved(previousIndex)
                }
            }
        }
    }

    fun clearMessageText() {
        message_text.text.clear()
    }
}