package com.daewon.presentation.signin

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import javax.inject.Inject

class EncryptedSharedPreferenceManager @Inject constructor(
    private val context: Context
) {
    companion object {
        private const val NICKNAME_KEY = "ID"
        private const val PWD_KEY = "PW"
        private const val AUTO_SIGNIN_KEY = "AUTO_SIGNIN"
    }

    private val sharedPrefsFile: String = "EncryptedSharedPreferences"
    private val masterKeyAlias =
        MasterKey.Builder(context).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build()
    private val encryptedSharedPreferences: SharedPreferences = EncryptedSharedPreferences.create(
        context,
        sharedPrefsFile,
        masterKeyAlias,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun canAutoSignIn() = encryptedSharedPreferences.getBoolean(AUTO_SIGNIN_KEY, false)

    fun signIn(nickname: String, pwd: String) = encryptedSharedPreferences.edit(true) {
        putString(NICKNAME_KEY, nickname)
        putString(PWD_KEY, pwd)
        putBoolean(AUTO_SIGNIN_KEY, true)
    }

    fun signOut(): Boolean = encryptedSharedPreferences.edit().remove(AUTO_SIGNIN_KEY).commit()

}