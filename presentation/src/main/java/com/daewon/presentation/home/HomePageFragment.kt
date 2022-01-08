package com.daewon.presentation.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.daewon.presentation.adapter.HomeCardAdapter
import com.daewon.presentation.adapter.HomeUserAdapter
import com.daewon.presentation.databinding.FragmentHomePageBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomePageFragment : Fragment() {
    private lateinit var binding: FragmentHomePageBinding
    private val viewModel: HomePageViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomePageBinding.inflate(inflater, container, false).apply {
            vm = viewModel
            lifecycleOwner = viewLifecycleOwner
        }
        context ?: return binding.root

        init()

        return binding.root
    }

    private fun init() {

        viewModel.getHomePage()

        viewModel.toastMsg.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }

}