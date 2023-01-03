package com.peter.foody.framework.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.peter.foody.R
import com.peter.foody.databinding.RowOffersBinding
import com.peter.foody.framework.datasource.responses.FoodResponse

class OffersAdapter(val onOfferClickListener: OnOfferClickListener) :
    ListAdapter<FoodResponse, OfferViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferViewHolder {
        val binding = DataBindingUtil.inflate<RowOffersBinding>(
            LayoutInflater.from(parent.context), R.layout.row_offers, parent, false
        )
        return OfferViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OfferViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
        holder.itemView.setOnClickListener {
            getItem(position)?.let { it1 -> onOfferClickListener.onClick(it1) }
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<FoodResponse>() {
        override fun areItemsTheSame(oldItem: FoodResponse, newItem: FoodResponse): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: FoodResponse, newItem: FoodResponse): Boolean {
            return oldItem.Barcode == newItem.Barcode
        }
    }
}

class OfferViewHolder(private var binding: RowOffersBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(Offer: FoodResponse) {
        binding.data = Offer
        binding.executePendingBindings()
    }
}

class OnOfferClickListener(val clickListener: (Offer: FoodResponse) -> Unit) {
    fun onClick(Offer: FoodResponse) = clickListener(Offer)
}