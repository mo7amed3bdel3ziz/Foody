package com.peter.foody.framework.presentation.categories

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.peter.foody.R
import com.peter.foody.business.model.foods.CategoryModel
import com.peter.foody.databinding.BillRowBinding

class AdapterCategory(val onReportClickListener: OnReportClickListener) :
    ListAdapter<CategoryModel, ReportViewHolder>(DiffCallback) {

    //onReportClickListener


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportViewHolder {
        val binding = DataBindingUtil.inflate<BillRowBinding>(
            LayoutInflater.from(parent.context), R.layout.bill_row, parent, false
        )
        return ReportViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReportViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
        holder.itemView.setOnClickListener {
            getItem(position)?.let { it1 -> onReportClickListener.onClick(it1,position) }
        }



    }

    companion object DiffCallback : DiffUtil.ItemCallback<CategoryModel>() {
        override fun areItemsTheSame(oldItem: CategoryModel, newItem: CategoryModel): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: CategoryModel, newItem: CategoryModel): Boolean {
            return oldItem.CategoryArName  == newItem.CategoryArName
        }
    }
}

class ReportViewHolder(private var binding: BillRowBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(salesReport: CategoryModel) {
      //  binding.categories = salesReport
        binding.executePendingBindings()
    }
}

class OnReportClickListener(val clickListener: (position: Int) -> Unit) {
    fun onClick(Category: CategoryModel, position: Int) = clickListener(position)
}