package com.example.kmmapplication

import platform.Foundation.NSUserDefaults
import platform.darwin.NSObject

actual typealias SPref = NSObject

actual fun SPref.getString(key: String) : String {
    return NSUserDefaults.standardUserDefaults.stringForKey(key) ?: ""
}

actual fun SPref.setString(key: String, value: String) {
    NSUserDefaults.standardUserDefaults.setObject(value, key)
}