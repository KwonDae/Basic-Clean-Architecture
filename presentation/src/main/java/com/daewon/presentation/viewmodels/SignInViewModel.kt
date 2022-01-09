package com.daewon.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.daewon.domain.model.SignInRes
import com.daewon.domain.usecase.GetLoginUseCase
import com.daewon.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val getLoginUseCase: GetLoginUseCase
) : BaseViewModel() {

    val id: MutableLiveData<String> = MutableLiveData("")
    val pw: MutableLiveData<String> = MutableLiveData("")
    private val _isIdEmpty: MutableLiveData<Unit> = MutableLiveData()
    private val _isPwEmpty: MutableLiveData<Unit> = MutableLiveData()
    private val _loginErrorMsg: MutableLiveData<String> = MutableLiveData()
    private val _successLogin: MutableLiveData<Unit> = MutableLiveData()

    val isIdEmpty: LiveData<Unit> get() = _isIdEmpty
    val isPwEmpty: LiveData<Unit> get() = _isPwEmpty
    val loginErrorMsg: LiveData<String> get() = _loginErrorMsg
    val successLogin: LiveData<Unit> get() = _successLogin

    fun onSignInClick() {
        val id = id.value.toString().trim()
        val pw = pw.value.toString().trim()

        when {
            id.isEmpty() -> {
                _isIdEmpty.value = Unit
            }
            pw.isEmpty() -> {
                _isPwEmpty.value = Unit
            }
            else -> {
                getLoginUseCase(id, pw, viewModelScope) {
                    if (it.status) {
                        // 로그인 성공
                        isLogin.value = true
                        _successLogin.value = Unit
                    } else {
                        // 로그인 실패
                        it.errorMsg?.let { errorMsg ->
                            _loginErrorMsg.value = errorMsg
                        }
                    }
                }
            }
        }

    }

    companion object {
        private const val USER_ID = "id"
        private const val USER_PW = "pass"
    }

}