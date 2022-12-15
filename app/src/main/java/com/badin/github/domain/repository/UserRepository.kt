package com.badin.github.domain.repository

import com.badin.github.domain.model.User
import com.badin.github.domain.model.UserShort

interface UserRepository {
    suspend fun searchUser(search: String): List<UserShort>
    suspend fun getUserRepository(name: String): User
}