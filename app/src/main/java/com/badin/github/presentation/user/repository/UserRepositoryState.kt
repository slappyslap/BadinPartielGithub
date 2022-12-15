package com.badin.github.presentation.user.repository

import com.badin.github.domain.model.User

sealed class UserRepositoryState {
    object Loading : UserRepositoryState()

    object Error : UserRepositoryState()

    class Success(val user: User) : UserRepositoryState()
}