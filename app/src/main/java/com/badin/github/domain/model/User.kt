package com.badin.github.domain.model

data class User(
    val id: Long,
    val login: String,
    val avatarUrl: String,
    val repositories: List<Repository>
)