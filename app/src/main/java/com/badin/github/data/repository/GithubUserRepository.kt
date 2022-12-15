package com.badin.github.data.repository

import com.badin.github.data.api.GithubApi
import com.badin.github.data.api.model.GithubRepository
import com.badin.github.data.api.model.GithubUserShort
import com.badin.github.domain.model.User
import com.badin.github.domain.model.UserShort
import com.badin.github.domain.repository.UserRepository
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GithubUserRepository : UserRepository {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(GithubApi.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api = retrofit.create(GithubApi::class.java)

    override suspend fun searchUser(search: String): List<UserShort> {
        return api.searchUser(search).items.map {
            it.toUserShort()
        }
    }

    override suspend fun getUserRepository(name: String): User {
        val datas = api.getUserRepository(name)
        println(datas)
        return User(
            id = datas[0].owner.id,
            login = datas[0].owner.login,
            avatarUrl = datas[0].owner.avatarUrl,
            repositories = datas.map {
                it.toRepository()
            }
        )
    }
}

private fun GithubUserShort.toUserShort() =
    UserShort(
        this.id,
        this.login,
        this.avatarUrl
    )

private fun GithubRepository.toRepository() =
    com.badin.github.domain.model.Repository(
        id = this.id,
        name = this.name,
        fullName = this.fullName,
        description = this.description ?: "",
        language = this.language ?: "",
        forks = this.forks,
        watchers = this.watchers,
        licence = this.licence ?: ""
    )
