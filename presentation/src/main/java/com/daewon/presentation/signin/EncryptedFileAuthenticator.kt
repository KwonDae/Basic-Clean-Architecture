package com.daewon.presentation.signin

import android.content.Context
import android.util.Log
import androidx.security.crypto.EncryptedFile
import androidx.security.crypto.MasterKey
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.io.File
import java.lang.Exception
import java.nio.charset.StandardCharsets
import javax.inject.Inject

class EncryptedFileAuthenticator @Inject constructor(
    context: Context
) {
    private val masterKey =
        MasterKey.Builder(context).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build()
    private val file = File(context.getExternalFilesDir(null), "data.txt")
    private val encryptedFile = EncryptedFile.Builder(
        context, file, masterKey, EncryptedFile.FileEncryptionScheme.AES256_GCM_HKDF_4KB
    ).build()

    suspend fun canAutoSignIn(): Boolean = withContext(Dispatchers.IO) {
        file.exists()
    }

    fun signIn(id: String, password: String, scope: CoroutineScope) {
        scope.launch(Dispatchers.IO) {
            try {
                encryptedFile.openFileOutput().use {
                    it.write("$id\n$password\n".toByteArray())
                    Timber.tag("보안").d( "signIn: content")
                }
            }catch (e: Exception) {
                e.printStackTrace()
            }

        }

    }

//    fun signIn(nickname: String, pwd: String, scope: CoroutineScope) {
//        scope.launch(Dispatchers.IO) {
//            try {
//                createFileIfNotExist()
//                val content = (nickname + "\n" + pwd)
//                Timber.tag("보안").d( "signIn: content")
//                val fileContent = content.toByteArray(StandardCharsets.UTF_8)
//                encryptedFile.openFileInput().apply {
//                    write(fileContent)
//                    flush()
//                    close()
//                }
//            } catch (e: Exception) {
//                e.printStackTrace()
//            }
//
//
//        }
//    }

    private fun createFileIfNotExist() {
        if (!file.exists()) file.createNewFile()
    }

    suspend fun signOut() {
        deleteFile()
    }

    private suspend fun deleteFile() = withContext(Dispatchers.IO) {
        file.delete()
    }
}