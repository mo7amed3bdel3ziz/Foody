package com.peter.foody.framework.presentation.ui.companyData

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.peter.foody.R
import com.peter.foody.data.roomContacts.onlineProduct.ItemsModel
import com.peter.foody.databinding.ItemRowBinding
import com.peter.foody.databinding.RowOffersBinding

class ItemAdapter (val onOfferClickListener: OnOfferClickListener) :
    ListAdapter<ItemsModel, OfferViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferViewHolder {
        val binding = DataBindingUtil.inflate<ItemRowBinding>(
            LayoutInflater.from(parent.context), R.layout.item_row, parent, false
        )
        return OfferViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OfferViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
        holder.itemView.setOnClickListener {
            getItem(position)?.let { it1 -> onOfferClickListener.onClick(it1) }
        }
      //  holder.editProductId


    }

    companion object DiffCallback : DiffUtil.ItemCallback<ItemsModel>() {
        override fun areItemsTheSame(oldItem: ItemsModel, newItem: ItemsModel): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: ItemsModel, newItem: ItemsModel): Boolean {
            return oldItem.Barcode == newItem.Barcode
        }
    }
}

class OfferViewHolder(private var binding: ItemRowBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(Offer: ItemsModel) {
        binding.data = Offer
        binding.executePendingBindings()
    }
}

class OnOfferClickListener(val clickListener: (Offer: ItemsModel) -> Unit) {
    fun onClick(Offer: ItemsModel) = clickListener(Offer)
}