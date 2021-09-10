package com.example.kmmapplication

import com.example.kmmapplication.db.AppDatabase
import com.example.kmmapplication.db.User

class UserDatabase(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = AppDatabase(databaseDriverFactory.createDriver())
    private val dbQuery = database.appDatabaseQueries

    fun addUser(name: String) =
        dbQuery.insertUser(null, name)

    fun addUserMultiple(name: String) =
        dbQuery.transaction {
            for (i in 0..2) {
                dbQuery.insertUser(null, name)
            }
        }

    fun getAllUsers(): List<User> = dbQuery.selectAllUsers().executeAsList()

    fun deleteAllUsers() = dbQuery.deleteAllUsers()
}