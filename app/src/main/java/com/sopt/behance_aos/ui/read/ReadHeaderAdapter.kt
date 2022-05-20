package com.sopt.behance_aos.ui.read

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sopt.behance_aos.databinding.ReadHeaderDetailItemBinding
import com.sopt.behance_aos.ui.read.model.ReadHeadData

class ReadHeaderAdapter() : ListAdapter<ReadHeadData, ReadHeaderAdapter.HeaderDetailViewHolder>(
    headerDiffUtil
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderDetailViewHolder {
        val binding =
            ReadHeaderDetailItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HeaderDetailViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HeaderDetailViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class HeaderDetailViewHolder(val binding: ReadHeaderDetailItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ReadHeadData) {
            binding.readHeadData = data
            binding.position = adapterPosition
        }
    }

    companion object {
        private val headerDiffUtil = object : DiffUtil.ItemCallback<ReadHeadData>() {
            override fun areItemsTheSame(oldItem: ReadHeadData, newItem: ReadHeadData): Boolean =
                oldItem.title == newItem.title

            override fun areContentsTheSame(oldItem: ReadHeadData, newItem: ReadHeadData): Boolean =
                oldItem == newItem
        }
    }
}
