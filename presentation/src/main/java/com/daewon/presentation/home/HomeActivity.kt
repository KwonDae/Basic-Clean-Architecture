package com.daewon.presentation.home

import android.os.Bundle
import androidx.databinding.DataBindingUtil.setContentView
import androidx.navigation.Navigation.findNavController
import androidx.navigation.findNavController
import com.daewon.presentation.R
import com.daewon.presentation.base.BaseActivity
import com.daewon.presentation.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>(R.layout.activity_home) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.signInButton.setOnClickListener {
            val direction = HomeViewPagerFragmentDirections.actionGlobalSignInFragment()
            findNavController(R.id.nav_host).navigate(direction)
        }
    }
}