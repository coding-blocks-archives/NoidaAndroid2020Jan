package com.pulkit.mvvm.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.retrofit.User
import com.pulkit.mvvm.R
import com.pulkit.mvvm.utils.string.SpannableLanguage
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_user.view.*

class UserAdapter(val data: List<User>) : RecyclerView.Adapter<UserAdapter.UserViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_user, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) =
        holder.bind(data[position])

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: User) = with(itemView) {
            textView.text = item.login?.let { SpannableLanguage(context, it) }
            Picasso.get().load(item.avatarUrl).into(imageView)
        }
    }
}