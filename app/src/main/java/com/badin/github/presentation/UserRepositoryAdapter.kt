package com.badin.github.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.badin.github.R
import com.badin.github.domain.model.Repository

class UserRepositoryAdapter(
    context: Context,
) : RecyclerView.Adapter<UserRepositoryAdapter.ViewHolder>() {

    private val repository: ArrayList<Repository> = ArrayList()

    private val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflater.inflate(R.layout.user_repository_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(repository[position])
    }

    override fun getItemCount() = repository.size

    @SuppressLint("NotifyDataSetChanged")
    fun setRepositories(data: List<Repository>?) {
        repository.clear()

        data?.let {
            repository.addAll(data)
        }

        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val name: TextView = view.findViewById(R.id.repository_item_name)
        private val description: TextView = view.findViewById(R.id.repository_item_description)
        private val language: TextView = view.findViewById(R.id.repository_item_language)
        private val forks: TextView = view.findViewById(R.id.repository_item_forks)
        private val watchers: TextView = view.findViewById(R.id.repository_item_watchers)
        private val licence: TextView = view.findViewById(R.id.repository_item_licence)


        fun bind(repository: Repository) {
            name.text = repository.name
            description.text = repository.description
            language.text = repository.language
            forks.text = repository.forks.toString()
            watchers.text = repository.watchers.toString()
            licence.text = repository.licence
        }
    }
}