package com.badin.github.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.badin.github.R
import com.badin.github.domain.model.UserShort
import com.squareup.picasso.Picasso

class UserShortAdapter(
    context: Context,
    private val listener: OnUserClickListener
) : RecyclerView.Adapter<UserShortAdapter.ViewHolder>() {

    interface OnUserClickListener {
        fun OnUserClicked(login: String)
    }

    private val users: ArrayList<UserShort> = ArrayList()

    private val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.user_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount() = users.size

    @SuppressLint("NotifyDataSetChanged")
    fun setMovies(data: List<UserShort>?) {
        users.clear()

        data?.let {
            users.addAll(data)
        }

        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val name: TextView = view.findViewById(R.id.user_item_name)
        private val avatar: ImageView = view.findViewById(R.id.user_item_avatar)

        init {
            view.setOnClickListener {
                listener.OnUserClicked(users[adapterPosition].login)
            }
        }

        fun bind(user: UserShort) {
            name.text = user.login

            Picasso.get().load(user.avatarUrl).into(avatar)
        }
    }
}