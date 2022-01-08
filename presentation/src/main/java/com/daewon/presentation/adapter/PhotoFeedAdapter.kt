package com.daewon.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.daewon.domain.model.Card
import com.daewon.presentation.databinding.ListItemPhotoFeedBinding
import com.daewon.presentation.home.HomeViewPagerFragmentDirections

class PhotoFeedAdapter :
    PagingDataAdapter<Card, PhotoFeedAdapter.PhotoFeedViewHolder>(PhotoFeedDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoFeedViewHolder {
        return PhotoFeedViewHolder(
            ListItemPhotoFeedBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PhotoFeedViewHolder, position: Int) {
        val card = getItem(position)
        if (card != null) {
            holder.bind(card)
        }
    }

    class PhotoFeedViewHolder(private val binding: ListItemPhotoFeedBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener { view ->
                binding.card?.let {
                    navigationToPhotoDetail(binding.card!!.id, view)
                }
            }
        }

        private fun navigationToPhotoDetail(cardId: Int, view: View) {
            val direction =
                HomeViewPagerFragmentDirections.actionViewPagerFragmentToPhotoDetailFragment(cardId)
            view.findNavController().navigate(direction)
        }

        fun bind(item: Card) {
            binding.apply {
                card = item
                executePendingBindings()
            }
        }
    }
}

private class PhotoFeedDiffCallback : DiffUtil.ItemCallback<Card>() {
    override fun areItemsTheSame(oldItem: Card, newItem: Card): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Card, newItem: Card): Boolean {
        return oldItem == newItem
    }

}