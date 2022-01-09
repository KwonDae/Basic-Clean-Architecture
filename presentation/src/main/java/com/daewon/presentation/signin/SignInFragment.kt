package com.daewon.presentation.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.daewon.presentation.R
import com.daewon.presentation.databinding.FragmentSignInBinding
import com.daewon.presentation.home.HomeViewPagerFragmentDirections
import com.daewon.presentation.viewmodels.SignInViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment: Fragment() {
    private val viewModel: SignInViewModel by viewModels()
    private lateinit var binding: FragmentSignInBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignInBinding.inflate(inflater, container, false).apply {
            vm = viewModel
            lifecycleOwner = viewLifecycleOwner
        }
        context ?: return binding.root

        initViewModelCallback()

        return binding.root

    }

    private fun initViewModelCallback() {
        with(viewModel) {
            isIdEmpty.observe(viewLifecycleOwner) {
                binding.idEditText.error = getString(R.string.id_empty_error_msg)
            }

            isPwEmpty.observe(viewLifecycleOwner) {
                binding.pwEditText.error = getString(R.string.pw_empty_error_msg)
//                Toast.makeText(context, R.string.pw_empty_error_msg, Toast.LENGTH_SHORT).show()
            }

            loginErrorMsg.observe(viewLifecycleOwner) {
                Toast.makeText(context, loginErrorMsg.value, Toast.LENGTH_SHORT).show()
            }

            successLogin.observe(viewLifecycleOwner) {
                Toast.makeText(context, "로그인 성공", Toast.LENGTH_SHORT).show()
                val direction = SignInFragmentDirections.actionSignInFragmentToViewPagerFragment()
                findNavController().navigate(direction)
            }
        }
    }

}