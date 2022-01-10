package com.daewon.presentation.home

import android.os.Bundle
import androidx.activity.viewModels
import androidx.navigation.findNavController
import com.daewon.presentation.R
import com.daewon.presentation.base.BaseActivity
import com.daewon.presentation.base.BaseViewModel.Companion.isLogin
import com.daewon.presentation.databinding.ActivityHomeBinding
import com.daewon.presentation.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>(R.layout.activity_home) {

    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        isLogin.value = autoLoginCheck()

        binding.signInButton.setOnClickListener {
            val direction = HomeViewPagerFragmentDirections.actionGlobalSignInFragment()
            findNavController(R.id.nav_host).navigate(direction)
        }

        binding.apply {
            bl = isLogin
            lifecycleOwner = this@HomeActivity

            signOutButton.setOnClickListener {
                isLogin.value = !viewModel.signOut()
            }
        }

    }

    private fun autoLoginCheck() = viewModel.canAutoSignIn()
}