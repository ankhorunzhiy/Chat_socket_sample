package com.sampleapp.ui.view

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.android.newssample.R
import com.sampleapp.domain.model.EventModel
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

    }

    class Adapter: RecyclerView.Adapter<RecyclerView.ViewHolder>(){

        lateinit var context: Context

        private enum class Type{
            MESSAGE, USER_STATUS, TYPING
        }

        var events: MutableList<EventModel> = ArrayList()

        override fun getItemCount(): Int = events.size

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
        }

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
            val inflater = LayoutInflater.from(context)
            when(viewType){
                Type.MESSAGE.ordinal -> return MessageHolder(inflater.inflate(R.layout.item_message, null))
                Type.USER_STATUS.ordinal -> return UserStatusHolder(inflater.inflate(R.layout.item_message, null))
                Type.TYPING.ordinal -> return TypingHolder(inflater.inflate(R.layout.item_message, null))
            }
            throw IllegalArgumentException("Unknown view type")
        }

        override fun getItemViewType(position: Int): Int {
            return events[position].event.ordinal
        }

        override fun onAttachedToRecyclerView(recyclerView: RecyclerView?) {
            super.onAttachedToRecyclerView(recyclerView)
            recyclerView?.let { this.context = context }
        }

        class MessageHolder(view: View) : RecyclerView.ViewHolder(view){

            fun bind(eventModel: EventModel){

            }

        }

        class TypingHolder(view: View): RecyclerView.ViewHolder(view){

            fun bind(eventModel: EventModel){

            }

        }

        class UserStatusHolder(view: View): RecyclerView.ViewHolder(view){

            fun bind(eventModel: EventModel){

            }

        }
    }
}