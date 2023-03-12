package com.peter.foody.framework.presentation.closingDay

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.peter.foody.R
import com.peter.foody.business.model.reports.SalesReport
import com.peter.foody.databinding.BillRowBinding
import com.peter.foody.databinding.ClosingdayRowBinding
import com.peter.foody.framework.presentation.reports.DataModel
import com.peter.foody.framework.presentation.reports.Items

class AdabterGetSaleReport  (val ReportClickListener:   ONReportClickListener) :
    ListAdapter<Items, ReportViewHolder>(DiffCallback) {

    //ONReportClickListener
    //onReportClickListener


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportViewHolder {
        val binding = DataBindingUtil.inflate<ClosingdayRowBinding>(
            LayoutInflater.from(parent.context), R.layout.closingday_row, parent, false
        )
        return ReportViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReportViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
        holder.itemView.setOnClickListener {
            getItem(position)?.let { it1 -> ReportClickListener.onClick(it1,position) }
        }



    }

    companion object DiffCallback : DiffUtil.ItemCallback<Items>() {
        override fun areItemsTheSame(oldItem: Items, newItem: Items): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Items, newItem: Items): Boolean {
            return oldItem.TotalQuantity  == newItem.TotalQuantity
        }
    }
}

class ReportViewHolder(private var binding: ClosingdayRowBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(dataModel: Items) {
        binding.data = dataModel
        binding.executePendingBindings()
    }
}

class   ONReportClickListener(val clickListener: (position: Int) -> Unit) {
    fun onClick(Category: Items, position: Int) = clickListener(position)
}