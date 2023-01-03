package com.peter.foody.framework.presentation.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.peter.foody.R
import com.peter.foody.business.model.foods.FoodBill
import com.peter.foody.databinding.RowCategoryBinding
import kotlinx.android.synthetic.main.fragment_main.view.*


class CategoriesAdapter(val onCategoryClickListener: OnCategoryClickListener) :
    ListAdapter<FoodBill, CategoryViewHolder>(DiffCallback) {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = DataBindingUtil.inflate<RowCategoryBinding>(
            LayoutInflater.from(parent.context), R.layout.row_category, parent, false
        )
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
        holder.itemView.setOnClickListener {
            getItem(position)?.let { it1 -> onCategoryClickListener.onClick(it1,position) }
        }



    }

    companion object DiffCallback : DiffUtil.ItemCallback<FoodBill>() {
        override fun areItemsTheSame(oldItem: FoodBill, newItem: FoodBill): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: FoodBill, newItem: FoodBill): Boolean {
            return oldItem.ItemID == newItem.ItemID
        }
    }
}

class CategoryViewHolder(private var binding: RowCategoryBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(Category: FoodBill) {
        binding.data = Category
        binding.executePendingBindings()
    }
}

class OnCategoryClickListener(val clickListener: (position: Int) -> Unit) {
    fun onClick(Category: FoodBill,position: Int) = clickListener(position)
}