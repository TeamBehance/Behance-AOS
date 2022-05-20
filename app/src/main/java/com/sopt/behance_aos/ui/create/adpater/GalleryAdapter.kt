package com.sopt.behance_aos.ui.create.adpater

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sopt.behance_aos.data.GalleryData
import com.sopt.behance_aos.databinding.ItemCreateGalleryBinding

class GalleryAdapter: RecyclerView.Adapter<GalleryAdapter.GalleryHolder>() {

    var galleryList= mutableListOf<GalleryData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryHolder {
        val binding = ItemCreateGalleryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GalleryHolder(binding)
    }

    override fun onBindViewHolder(holder: GalleryHolder, position: Int) {
        holder.onBind(galleryList[position])
    }

    override fun getItemCount(): Int = galleryList.size

    inner class GalleryHolder(private var binding:ItemCreateGalleryBinding):RecyclerView.ViewHolder(binding.root){
        fun onBind(data:GalleryData){
            binding.galleryRecycler = data
        }
    }
}