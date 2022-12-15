package com.badin.github.presentation.search.user

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.badin.github.R
import com.badin.github.presentation.MainActivity
import com.badin.github.presentation.UserShortAdapter

class SearchUserFragment : Fragment(), UserShortAdapter.OnUserClickListener {
    companion object {
        fun newInstance() = SearchUserFragment()
    }

    private lateinit var editText: EditText
    private lateinit var button: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar

    private lateinit var adapter: UserShortAdapter

    private val viewModel: SearchUserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.state.observe(viewLifecycleOwner) {
            onStateChanged(it)
        }

        editText = view.findViewById(R.id.fragment_search_user_edittext)
        button = view.findViewById(R.id.fragment_search_user_button)
        recyclerView = view.findViewById(R.id.fragment_search_user_recyclerview)
        progressBar = view.findViewById(R.id.fragment_search_user_progress)

        adapter = UserShortAdapter(requireContext(), this)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        button.setOnClickListener {
            viewModel.searchUser(editText.text.toString())
        }

    }

    private fun onStateChanged(state: SearchUserState) {
        when (state) {
            is SearchUserState.Loading -> {

                progressBar.visibility = View.VISIBLE

                adapter.setMovies(null)
            }
            is SearchUserState.Success -> {

                progressBar.visibility = View.GONE

                adapter.setMovies(state.users)
            }
            is SearchUserState.Error -> {
                progressBar.visibility = View.GONE

                adapter.setMovies(null)

                Toast.makeText(activity, "Error", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun OnUserClicked(login: String) {
        (activity as? MainActivity)?.displayUserRepositories(login)
    }
}