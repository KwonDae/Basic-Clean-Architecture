package com.daewon.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.daewon.domain.model.Card
import com.daewon.presentation.databinding.HomeListItemPhotoBinding

class HomeCardAdapter : ListAdapter<Card, RecyclerView.ViewHolder>(CardDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CardViewHolder(
            HomeListItemPhotoBinding.inflate(
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
        private val binding: HomeListItemPhotoBinding
    ): RecyclerView.ViewHolder(binding.root) {
        init {

        }

        fun bind(item: Card) {
            binding.apply {
                card = item
                executePendingBindings()
            }
        }
    }

}

private class CardDiffCallback : DiffUtil.ItemCallback<Card>() {
    override fun areItemsTheSame(oldItem: Card, newItem: Card): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Card, newItem: Card): Boolean {
        return oldItem == newItem
    }

}