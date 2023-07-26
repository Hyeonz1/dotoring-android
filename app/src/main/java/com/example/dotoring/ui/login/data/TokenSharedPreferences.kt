package com.example.dotoring.ui.login.data

import android.content.Context
import android.content.SharedPreferences

class TokenSharedPreferences(context: Context) {
    private val prefsFilename = "token_prefs"
    private val key_accessToken = "accesToken"
    private val key_refreshToken = "refreshToken"
    private val prefs: SharedPreferences = context.getSharedPreferences(prefsFilename,0)


    var accessToken: String?
        get() = prefs.getString(key_accessToken,"")
        set(value) = prefs.edit().putString(key_accessToken,value).apply()

    var refreshToken: String?
        get() = prefs.getString(key_refreshToken,"")
        set(value) = prefs.edit().putString(key_refreshToken,value).apply()
}