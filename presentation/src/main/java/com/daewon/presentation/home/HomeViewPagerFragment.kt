package com.daewon.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.daewon.presentation.adapter.HOME_PAGE_INDEX
import com.daewon.presentation.adapter.HomePagerAdapter
import com.daewon.presentation.adapter.PHOTO_FEED_INDEX
import com.daewon.presentation.databinding.FragmentViewPagerBinding
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeViewPagerFragment : Fragment() {
    private lateinit var binding: FragmentViewPagerBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentViewPagerBinding.inflate(inflater, container, false)
        context ?: return binding.root

        init()
        return binding.root
    }

    private fun getTabTitle(position: Int): String? {
        return when (position) {
            HOME_PAGE_INDEX -> "홈"
            PHOTO_FEED_INDEX -> "사진피드"
            else -> null
        }
    }

    private fun init() {
        val tabLayout = binding.tabs
        val viewPager = binding.viewPager

        viewPager.adapter = HomePagerAdapter(this)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = getTabTitle(position)
        }.attach()
    }

}