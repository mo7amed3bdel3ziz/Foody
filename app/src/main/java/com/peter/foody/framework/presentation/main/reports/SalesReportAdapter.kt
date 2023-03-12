package com.peter.foody.framework.presentation.main.reports

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.peter.foody.R
import com.peter.foody.business.model.reports.SalesReport
import com.peter.foody.databinding.BillRowBinding
import com.peter.foody.framework.presentation.reports.DataModel

class SalesReportAdapter (val onReportClickListener: OnReportClickListener) :
    ListAdapter<DataModel, ReportViewHolder>(DiffCallback) {

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

    companion object DiffCallback : DiffUtil.ItemCallback<DataModel>() {
        override fun areItemsTheSame(oldItem: DataModel, newItem: DataModel): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: DataModel, newItem: DataModel): Boolean {
            return oldItem.Quantity  == newItem.Quantity
        }
    }
}

class ReportViewHolder(private var binding: BillRowBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(dataModel: DataModel) {
        binding.data = dataModel
        binding.executePendingBindings()
    }
}

class OnReportClickListener(val clickListener: (position: Int) -> Unit) {
    fun onClick(Category: DataModel, position: Int) = clickListener(position)
}