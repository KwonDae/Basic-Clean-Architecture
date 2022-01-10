package com.daewon.presentation.photofeed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import com.daewon.presentation.adapter.PhotoFeedAdapter
import com.daewon.presentation.databinding.FragmentPhotoFeedBinding
import com.daewon.presentation.viewmodels.PhotoFeedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

@AndroidEntryPoint
class PhotoFeedFragment : Fragment() {
    private val viewModel: PhotoFeedViewModel by viewModels()
    private lateinit var binding: FragmentPhotoFeedBinding
    private var searchJob: Job? = null
    private val adapter = PhotoFeedAdapter()
    private var isFirst: Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPhotoFeedBinding.inflate(inflater, container, false)
        context ?: return binding.root

        init()
//        initSearch()

        return binding.root
    }

    private fun init() {
        if(isFirst) {
            isFirst = false
            getData()
        }
        binding.apply {
            vm = viewModel
            lifecycleOwner = viewLifecycleOwner
            photoFeedRecyclerView.adapter = adapter
            swipeRefreshLayout.setOnRefreshListener {
                getData()
            }
        }
    }

    private fun getData() {
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            viewModel.searchPhoto().collectLatest {
                adapter.submitData(it)
            }
        }
    }
}