package com.peter.foody.framework.presentation.ui.receipts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.peter.foody.R
 import com.peter.foody.data.roomContacts.HeaderBill
 import com.peter.foody.databinding.SellsLayoutBinding

class AdabterBill() :
    ListAdapter<HeaderBill, CategoryViewHolder>(DiffCallback) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = DataBindingUtil.inflate<SellsLayoutBinding>(
            LayoutInflater.from(parent.context), R.layout.sells_layout, parent, false
        )

        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
//        holder.itemView.setOnClickListener {
//            getItem(position)?.let { it1 -> onCategoryClickListener.onClick(it1, position) }
//        }


    }

    companion object DiffCallback : DiffUtil.ItemCallback<HeaderBill>() {
        override fun areItemsTheSame(oldItem: HeaderBill, newItem: HeaderBill): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: HeaderBill, newItem: HeaderBill): Boolean {
            return oldItem.BillNumber == newItem.BillNumber
        }
    }
}

class CategoryViewHolder(private var binding: SellsLayoutBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(Category: HeaderBill) {
        binding.data = Category
        binding.executePendingBindings()
    }
}

class OnCategoryClickListener(val clickListener: (position: Int) -> Unit) {
    fun onClick(Category: HeaderBill, position: Int) = clickListener(position)
}