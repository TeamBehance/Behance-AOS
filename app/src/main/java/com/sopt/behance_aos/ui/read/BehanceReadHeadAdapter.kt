package com.sopt.behance_aos.ui.read

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sopt.behance_aos.databinding.ListBehanceReadHeadBinding


class BehanceReadHeadAdapter : RecyclerView.Adapter<BehanceReadHeadAdapter.HeadViewHolder>() {
    lateinit var headList: List<BehanceReadHeadData>

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HeadViewHolder {
        return HeadViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: HeadViewHolder, position: Int) {
        holder.bind(headList[position])
    }

    override fun getItemCount() = headList.size

    class HeadViewHolder(val binding: ListBehanceReadHeadBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: BehanceReadHeadData) {
            binding.head = data
        }

        companion object {
            fun from(parent: ViewGroup): HeadViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListBehanceReadHeadBinding.inflate(layoutInflater, parent, false)

                return HeadViewHolder(binding)
            }
        }
    }
}