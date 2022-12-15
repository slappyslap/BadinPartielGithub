package com.badin.github.presentation.user.repository

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.badin.github.R
import com.badin.github.presentation.UserRepositoryAdapter
import com.squareup.picasso.Picasso

class UserRepositoryFragment(private val user: String) : Fragment() {
    companion object {
        fun newInstance(user: String) = UserRepositoryFragment(user)
    }

    private lateinit var name: TextView
    private lateinit var avatar: ImageView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: UserRepositoryAdapter

    private lateinit var recyclerView: RecyclerView

    private val viewModel: UserRepositoryViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_repository, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.state.observe(viewLifecycleOwner) {
            onStateChanged(it)
        }

        name = view.findViewById(R.id.fragment_user_repository_name)
        avatar = view.findViewById(R.id.fragment_user_repository_avatar)
        progressBar = view.findViewById(R.id.fragment_user_repository_progress)
        recyclerView = view.findViewById(R.id.fragment_user_repository_recyclerview)

        adapter = UserRepositoryAdapter(requireContext())

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        viewModel.getUserRepository(user)
    }

    private fun onStateChanged(state: UserRepositoryState) {
        when (state) {
            is UserRepositoryState.Loading -> {
                progressBar.visibility = View.VISIBLE
            }
            is UserRepositoryState.Success -> {
                progressBar.visibility = View.GONE

                name.text = getString(
                    R.string.username_repo_count,
                    state.user.login,
                    state.user.repositories.size
                )

                name.text = state.user.login + " (" + state.user.repositories.size + ")"
                Picasso.get().load(state.user.avatarUrl).into(avatar)
                adapter.setRepositories(state.user.repositories)
            }

            is UserRepositoryState.Error -> {
                progressBar.visibility = View.GONE
            }
        }
    }
}