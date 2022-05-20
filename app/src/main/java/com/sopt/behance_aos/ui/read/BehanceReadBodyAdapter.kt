package com.sopt.behance_aos.ui.read

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sopt.behance_aos.databinding.ListBehanceReadBodyBinding

class BehanceReadBodyAdapter : RecyclerView.Adapter<BehanceReadBodyAdapter.BodyViewHolder>() {
    lateinit var bodyList: List<BehanceReadBodyData>

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BodyViewHolder {
        return BodyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: BodyViewHolder, position: Int) {
        holder.bind(bodyList[position])
    }

    override fun getItemCount() = bodyList.size

    class BodyViewHolder(val binding: ListBehanceReadBodyBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: BehanceReadBodyData) {
            binding.body = data
        }

        companion object {
            fun from(parent: ViewGroup): BodyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ListBehanceReadBodyBinding.inflate(layoutInflater, parent, false)

                return BodyViewHolder(binding)
            }
        }
    }
}