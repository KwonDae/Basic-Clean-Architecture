package com.daewon.presentation.viewmodels

import com.daewon.presentation.base.BaseViewModel
import com.daewon.presentation.signin.EncryptedSharedPreferenceManager
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val encryptedSharedPreferenceManager: EncryptedSharedPreferenceManager
): BaseViewModel() {


    fun signOut(): Boolean = encryptedSharedPreferenceManager.signOut()
    fun canAutoSignIn(): Boolean = encryptedSharedPreferenceManager.canAutoSignIn()

}