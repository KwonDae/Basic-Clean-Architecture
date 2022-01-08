package com.daewon.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.daewon.domain.model.User
import com.daewon.presentation.databinding.ListItemUserBinding

class HomeUserAdapter : ListAdapter<User, RecyclerView.ViewHolder>(UserDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return UserViewHolder(
            ListItemUserBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val user = getItem(position)
        (holder as UserViewHolder).bind(user)
    }

    class UserViewHolder(
        private val binding: ListItemUserBinding
    ): RecyclerView.ViewHolder(binding.root) {
        init {

        }

        fun bind(item: User) {
            binding.apply {
                user = item
                executePendingBindings()
            }
        }
    }

}

private class UserDiffCallback : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }

}