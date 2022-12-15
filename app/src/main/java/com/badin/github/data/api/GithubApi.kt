package com.badin.github.data.api

import com.badin.github.data.api.model.GithubRepository
import com.badin.github.data.api.model.GithubSearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {
    companion object {
        const val BASE_URL = "https://api.github.com/"
    }

    //https://api.github.com/search/users?q=badin
    @GET("search/users")
    suspend fun searchUser(@Query("q") query: String): GithubSearchResponse

    //https://api.github.com/users/badin/repos
    @GET("users/{user}/repos")
    suspend fun getUserRepository(@Path("user") user: String): List<GithubRepository>
}