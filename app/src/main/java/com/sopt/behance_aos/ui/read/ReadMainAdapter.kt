package com.sopt.behance_aos.ui.read


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sopt.behance_aos.databinding.ReadBodyItemBinding
import com.sopt.behance_aos.databinding.ReadHeaderItemBinding
import com.sopt.behance_aos.ui.read.model.ReadBodyData
import java.lang.RuntimeException

class ReadMainAdapter(private val readHeaderAdapter: ReadHeaderAdapter) :
    ListAdapter<ReadBodyData, RecyclerView.ViewHolder>(mainDiffUtil) {

    private lateinit var readHeaderItemBinding: ReadHeaderItemBinding
    private lateinit var readBodyItemBinding: ReadBodyItemBinding

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> HEADER_ITEM
            else -> BODY_ITEM
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            HEADER_ITEM -> {
                readHeaderItemBinding =
                    ReadHeaderItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                ReadHeaderViewHolder(readHeaderItemBinding)
            }
            BODY_ITEM -> {
                readBodyItemBinding =
                    ReadBodyItemBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                ReadBodyViewHolder(readBodyItemBinding)
            }
            else -> {
                throw RuntimeException("알 수 없는 viewType error")
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ReadHeaderViewHolder) {
            holder.onBind(readHeaderAdapter = readHeaderAdapter)
        } else if (holder is ReadBodyViewHolder) {
            holder.onBind(getItem(position))
        }
    }

    class ReadHeaderViewHolder(val binding: ReadHeaderItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(readHeaderAdapter: ReadHeaderAdapter) {
            binding.rvReadHeader.adapter = readHeaderAdapter
        }
    }

    class ReadBodyViewHolder(val binding: ReadBodyItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(data: ReadBodyData) {
            binding.readBodyData = data
        }
    }

    companion object {
        private val mainDiffUtil = object : DiffUtil.ItemCallback<ReadBodyData>() {
            override fun areItemsTheSame(oldItem: ReadBodyData, newItem: ReadBodyData): Boolean =
                oldItem.title == newItem.title

            override fun areContentsTheSame(oldItem: ReadBodyData, newItem: ReadBodyData): Boolean =
                oldItem == newItem
        }

        private const val HEADER_ITEM = 0
        private const val BODY_ITEM = 1
    }
}
