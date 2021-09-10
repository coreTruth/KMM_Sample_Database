package com.example.kmmapplication

expect class SPref

expect fun SPref.getString(key: String) : String
expect fun SPref.setString(key: String, value: String)