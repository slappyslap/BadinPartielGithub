package com.badin.github.domain.model

data class Repository(
    val id: Long,
    val name: String,
    val fullName: String,
    val description: String,
    val language: String?,
    val forks: Int,
    val watchers: Int,
    val licence: String?,
)