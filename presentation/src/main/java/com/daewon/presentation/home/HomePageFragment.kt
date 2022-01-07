package com.daewon.presentation.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.daewon.presentation.adapter.HomeCardAdapter
import com.daewon.presentation.adapter.HomeUserAdapter
import com.daewon.presentation.databinding.FragmentHomePageBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomePageFragment : Fragment() {
    private lateinit var binding: FragmentHomePageBinding
    private val viewModel: HomePageViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomePageBinding.inflate(inflater, container, false)
        context ?: return binding.root

        init()

        return binding.root
    }

    private fun init() {
        val cardAdapter = HomeCardAdapter()
        val userAdapter = HomeUserAdapter()
        subscribeUi(cardAdapter, userAdapter)

        viewModel.getHomePage()

        binding.apply {
            refreshLayout.setOnRefreshListener {
                viewModel.getHomePage()
            }

            photoRecyclerView.adapter = cardAdapter
            userRecyclerView.adapter = userAdapter
            photoRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            userRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }

        viewModel.toastMsg.observe(viewLifecycleOwner) {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }

    private fun subscribeUi(homeCardAdapter: HomeCardAdapter, homeUserAdapter: HomeUserAdapter) {
        viewModel.homePageData.observe(viewLifecycleOwner) { data ->
            homeCardAdapter.submitList(data.cards)
            homeUserAdapter.submitList(data.users)
            binding.refreshLayout.isRefreshing = false
        }
    }

}