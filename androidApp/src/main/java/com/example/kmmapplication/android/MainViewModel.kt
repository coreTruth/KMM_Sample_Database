package com.example.kmmapplication.android

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.kmmapplication.DatabaseDriverFactory
import com.example.kmmapplication.KMMStorage
import com.example.kmmapplication.UserDatabase
import com.example.kmmapplication.db.User

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private var userDatabase = UserDatabase(DatabaseDriverFactory(application))
    private val _appUsers: MutableLiveData<List<User>> = MutableLiveData()
    val appUsers: LiveData<List<User>>
        get() = _appUsers

    init {
        updateUsers()
    }

    fun updateUsers() {
        _appUsers.value = mutableListOf<User>().apply {
            addAll(userDatabase.getAllUsers())
        }
    }

    fun deleteAllUsers() {
        userDatabase.deleteAllUsers()
        updateUsers()
    }

    fun addUser(name: String) {
        userDatabase.addUser(name)
        updateUsers()
    }

    fun addMultipleUser(name: String) {
        userDatabase.addUserMultiple(name)
        updateUsers()
    }
}