package com.sopt.behance_aos.ui.read

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sopt.behance_aos.databinding.ListBehanceReadBodyBinding

class BehanceReadBodyAdapter : RecyclerView.Adapter<BehanceReadBodyAdapter.BehanceReadBodyViewHolder>() {
    val behanceReadBodyList = mutableListOf<BehanceReadBodyData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BehanceReadBodyViewHolder {
        val binding =
            ListBehanceReadBodyBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        return BehanceReadBodyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BehanceReadBodyViewHolder, position: Int) {
        holder.onBind(behanceReadBodyList[position])
    }

    override fun getItemCount(): Int = behanceReadBodyList.size

    class BehanceReadBodyViewHolder(
        private val binding: ListBehanceReadBodyBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: BehanceReadBodyData) {
            binding.tvBehanceReadBodyTitle.text = data.title
            binding.tvBehanceReadBodyName.text = data.name
        }
    }
}
