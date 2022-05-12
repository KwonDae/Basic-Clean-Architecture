package com.daewon.presentation.signin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.daewon.presentation.R
import com.daewon.presentation.databinding.FragmentSignInBinding
import com.daewon.presentation.viewmodels.SignInViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : Fragment() {
    private val viewModel: SignInViewModel by viewModels()

    private var _binding: FragmentSignInBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignInBinding.inflate(inflater, container, false).apply {
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
            }

            loginErrorMsg.observe(viewLifecycleOwner) {
                Toast.makeText(context, loginErrorMsg.value, Toast.LENGTH_SHORT).show()
            }

            successLogin.observe(viewLifecycleOwner) {
                findNavController().popBackStack()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}