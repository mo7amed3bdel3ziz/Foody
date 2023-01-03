package com.peter.foody.framework.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.peter.foody.R
import com.peter.foody.business.model.Slider
import com.peter.foody.databinding.RowSlidersBinding

class SlidersAdapter(val onSliderClickListener: OnSliderClickListener) :
    ListAdapter<Slider, SliderViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SliderViewHolder {
        val binding = DataBindingUtil.inflate<RowSlidersBinding>(
            LayoutInflater.from(parent.context), R.layout.row_sliders, parent, false
        )
        return SliderViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
        holder.itemView.setOnClickListener {
            getItem(position)?.let { it1 -> onSliderClickListener.onClick(it1) }
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Slider>() {
        override fun areItemsTheSame(oldItem: Slider, newItem: Slider): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Slider, newItem: Slider): Boolean {
            return oldItem.photo == newItem.photo
        }
    }
}

class SliderViewHolder(private var binding: RowSlidersBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(Slider: Slider) {
        binding.data = Slider
        binding.executePendingBindings()
    }
}

class OnSliderClickListener(val clickListener: (Slider: Slider) -> Unit) {
    fun onClick(Slider: Slider) = clickListener(Slider)
}