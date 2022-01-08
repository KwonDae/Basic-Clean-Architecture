package com.daewon.presentation.adapter

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

object RecyclerViewDataBinding {

    @JvmStatic
    @BindingAdapter(
        value = ["bind:listAdapter", "bind:orientation", "bind:items"],
        requireAll = false
    )
    fun bindRecyclerViewListAdapter(
        recyclerView: RecyclerView,
        adapter: ListAdapter<*, *>,
        orientation: Int = RecyclerView.HORIZONTAL,
        items: List<*>?
    ) {
        recyclerView.setHasFixedSize(true)

        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context, orientation, false)

        items?.let {
            adapter.submitList(it as List<Nothing>?)
        }

        recyclerView.adapter = adapter
    }
}
