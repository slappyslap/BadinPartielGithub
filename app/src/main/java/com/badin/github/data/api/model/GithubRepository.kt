package com.badin.github.data.api.model

import com.google.gson.annotations.SerializedName

data class GithubRepository(
    val id: Long,
    val name: String,

    @SerializedName("full_name")
    val fullName: String,
    val description: String?,
    val forks: Int,
    val watchers: Int,
    val language: String?,

    @SerializedName("licence.name")
    val licence: String?,
    val owner: GithubUserShort,
)