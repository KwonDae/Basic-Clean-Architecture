package com.daewon.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.daewon.presentation.home.HomePageFragment
import com.daewon.presentation.photo.PhotoFeedFragment
import java.lang.IndexOutOfBoundsException

const val HOME_PAGE_INDEX = 0
const val PHOTO_FEED_INDEX = 1

class HomePagerAdapter(fragment: Fragment): FragmentStateAdapter(fragment) {

    /**
     * 뷰페이저와 프래그먼트 연결
     */
    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
        HOME_PAGE_INDEX to { HomePageFragment() },
        PHOTO_FEED_INDEX to { PhotoFeedFragment() }
    )

    override fun getItemCount(): Int {
        return tabFragmentsCreators.size
    }

    override fun createFragment(position: Int): Fragment {
        return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}