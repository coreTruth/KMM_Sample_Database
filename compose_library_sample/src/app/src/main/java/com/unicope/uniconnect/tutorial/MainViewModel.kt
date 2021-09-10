package com.unicope.uniconnect.tutorial

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val _users: MutableLiveData<List<String>> = MutableLiveData()
    val users: LiveData<List<String>>
        get() = _users

    init {
        _users.value = listOf()
    }

    fun deleteAllUsers() {
        _users.value = listOf()
    }

    fun addUser(name: String) {
        _users.value = _users.value?.plus(name)
    }

    fun addMultipleUser(name: String) {
        for (i in 0..2) {
            _users.value = _users.value?.plus(name)
        }
    }
}