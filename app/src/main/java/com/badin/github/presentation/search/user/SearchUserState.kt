package com.badin.github.presentation.search.user

import com.badin.github.domain.model.UserShort

sealed class SearchUserState {
    class Success(val users: List<UserShort>) : SearchUserState()
    object Loading : SearchUserState()
    object Error : SearchUserState()
}