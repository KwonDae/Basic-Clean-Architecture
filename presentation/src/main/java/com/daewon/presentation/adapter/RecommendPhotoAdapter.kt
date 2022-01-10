package com.daewon.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.daewon.domain.model.Card
import com.daewon.presentation.databinding.ListItemPhotoBinding
import com.daewon.presentation.photo.PhotoDetailFragmentDirections

class RecommendPhotoAdapter: ListAdapter<Card, RecyclerView.ViewHolder>(RecommendCardDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CardViewHolder(
            ListItemPhotoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val card = getItem(position)
        (holder as CardViewHolder).bind(card)
    }

    class CardViewHolder(
        private val binding: ListItemPhotoBinding
    ): RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener { view ->
                binding.card?.let {
                    navigationToPhotoDetail(binding.card!!.id, view)
                }
            }
        }

        private fun navigationToPhotoDetail(cardId: Int, view: View) {
            val direction =
                PhotoDetailFragmentDirections.actionPhotoDetailFragmentSelf(cardId)
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

private class RecommendCardDiffCallback : DiffUtil.ItemCallback<Card>() {
    override fun areItemsTheSame(oldItem: Card, newItem: Card): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Card, newItem: Card): Boolean {
        return oldItem == newItem
    }

}