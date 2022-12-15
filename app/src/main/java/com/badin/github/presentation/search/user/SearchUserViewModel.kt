package com.badin.github.presentation.search.user

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.badin.github.data.repository.GithubUserRepository
import com.badin.github.domain.repository.UserRepository
import kotlinx.coroutines.launch

class SearchUserViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: UserRepository = GithubUserRepository()

    private val _state = MutableLiveData<SearchUserState>()

    val state: LiveData<SearchUserState> get() = _state

    fun searchUser(query: String) {
        _state.value = SearchUserState.Loading
        viewModelScope.launch {
            try {
                _state.value = SearchUserState.Success(repository.searchUser(query))
            } catch (e: Exception) {
                Log.e("catch", e.message.toString());
                _state.value = SearchUserState.Error
            }
        }
    }
}