package com.example.kmmapplication

class KMMStorage(val context: SPref) {
    object KeyConstants {
        const val LAST_USER_KEY = "LAST_USER"
    }

    fun getLastUser(): String {
        return context.getString(KeyConstants.LAST_USER_KEY)
    }

    fun putLastUser(value: String) {
        context.setString(KeyConstants.LAST_USER_KEY, value)
    }
}