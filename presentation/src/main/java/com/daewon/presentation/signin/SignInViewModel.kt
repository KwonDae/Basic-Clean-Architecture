package com.daewon.presentation.signin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.daewon.domain.repository.RemoteRepository
import com.daewon.domain.usecase.GetLoginUseCase
import com.daewon.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val getLoginUseCase: GetLoginUseCase
): BaseViewModel() {

    val id: MutableLiveData<String> = MutableLiveData("")
    val pw: MutableLiveData<String> = MutableLiveData("")
    private val _isIdEmpty: MutableLiveData<Unit> = MutableLiveData()
    private val _isPwEmpty: MutableLiveData<Unit> = MutableLiveData()
    private val _loginErrorMsg: MutableLiveData<Unit> = MutableLiveData()
    private val _successLogin: MutableLiveData<Unit> = MutableLiveData()

    val isIdEmpty: LiveData<Unit> get() = _isIdEmpty
    val isPwEmpty: LiveData<Unit> get() = _isPwEmpty
    val loginErrorMsg: LiveData<Unit> get() = _loginErrorMsg
    val successLogin: LiveData<Unit> get() = _successLogin

    fun onSignInClick() {
        val id = id.value.toString().trim()
        val pw = pw.value.toString().trim()
    }

    companion object {
        private const val USER_ID = "id"
        private const val USER_PW = "pass"
    }

}