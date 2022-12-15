package com.badin.github.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.badin.github.R
import com.badin.github.presentation.search.user.SearchUserFragment
import com.badin.github.presentation.user.repository.UserRepositoryFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.activity_main_container, SearchUserFragment.newInstance())
                .commit()
        }
    }

    fun displayUserRepositories(login: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.activity_main_container, UserRepositoryFragment.newInstance(login))
            .addToBackStack(null)
            .commit()
    }
}