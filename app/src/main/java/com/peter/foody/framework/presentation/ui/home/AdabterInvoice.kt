package com.peter.foody.framework.presentation.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.peter.foody.R
import com.peter.foody.data.remote.model.models.ItemsModels
import com.peter.foody.databinding.BillRowHomeBinding

class AdabterInvoice (val onCategoryClickListener: OnCategoryClickListener) :
    ListAdapter<ItemsModels, CategoryViewHolder>(DiffCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = DataBindingUtil.inflate<BillRowHomeBinding>(
            LayoutInflater.from(parent.context), R.layout.bill_row_home, parent, false
        )

        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
        holder.itemView.setOnClickListener {
            getItem(position)?.let { it1 -> onCategoryClickListener.onClick(it1, position) }
        }


    }

    companion object DiffCallback : DiffUtil.ItemCallback<ItemsModels>() {
        override fun areItemsTheSame(oldItem: ItemsModels, newItem: ItemsModels): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: ItemsModels, newItem: ItemsModels): Boolean {
            return oldItem.Record_ID == newItem.Record_ID
        }
    }
}

class CategoryViewHolder(private var binding: BillRowHomeBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(Category: ItemsModels) {
        binding.data = Category
        binding.executePendingBindings()
    }
}

class OnCategoryClickListener(val clickListener: (position: Int) -> Unit) {
    fun onClick(Category: ItemsModels, position: Int) = clickListener(position)
}