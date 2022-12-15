package com.badin.github.presentation.user.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.badin.github.data.repository.GithubUserRepository
import com.badin.github.domain.repository.UserRepository
import kotlinx.coroutines.launch

class UserRepositoryViewModel : ViewModel() {
    private val _state = MutableLiveData<UserRepositoryState>()

    val state: LiveData<UserRepositoryState> = _state

    private val repository: UserRepository = GithubUserRepository()

    fun getUserRepository(username: String) {
        _state.value = UserRepositoryState.Loading
        viewModelScope.launch {
            try {
                _state.value = UserRepositoryState.Success(repository.getUserRepository(username))
            } catch (e: Exception) {
                Log.e("catch", e.message.toString());
                _state.value = UserRepositoryState.Error
            }
        }
    }

}