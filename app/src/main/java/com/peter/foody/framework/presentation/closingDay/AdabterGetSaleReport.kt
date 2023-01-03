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

class AdabterGetSaleReport  (val ReportClickListener:   ONReportClickListener) :
    ListAdapter<SalesReport, ReportViewHolder>(DiffCallback) {

    //ONReportClickListener
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
            getItem(position)?.let { it1 -> ReportClickListener.onClick(it1,position) }
        }



    }

    companion object DiffCallback : DiffUtil.ItemCallback<SalesReport>() {
        override fun areItemsTheSame(oldItem: SalesReport, newItem: SalesReport): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: SalesReport, newItem: SalesReport): Boolean {
            return oldItem.totalQty  == newItem.totalQty
        }
    }
}

class ReportViewHolder(private var binding: BillRowBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(salesReport: SalesReport) {
        binding.salesReport = salesReport
        binding.executePendingBindings()
    }
}

class   ONReportClickListener(val clickListener: (position: Int) -> Unit) {
    fun onClick(Category: SalesReport, position: Int) = clickListener(position)
}