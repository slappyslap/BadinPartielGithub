package com.badin.github.data.api.model

import com.google.gson.annotations.SerializedName

class GithubUserShort {
    val login: String = ""
    val id: Long = 0

    @SerializedName("avatar_url")
    val avatarUrl: String = ""
}