package com.pulkit.chatapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_message.view.*
import java.util.*

class MessageAdapter : RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {

    private var data: List<Message> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        return MessageViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_message, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) =
        holder.bind(data[position])

    fun swapData(data: List<Message>) {
        this.data = data
        notifyDataSetChanged()
    }

    class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Message) = with(itemView) {
            nameTv.text = item.name
            msgTv.text = item.msg
        }
    }
}