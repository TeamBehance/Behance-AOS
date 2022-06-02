package com.sopt.behance_aos.ui.create.adpater

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sopt.behance_aos.data.MediaStoreImage
import com.sopt.behance_aos.databinding.ItemCreateGalleryBinding

class GalleryAdapter :
    ListAdapter<MediaStoreImage, ImageViewHolder>(imageDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ItemCreateGalleryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val mediaStoreImage = getItem(position)

        Glide.with(holder.imageView)
            .load(mediaStoreImage.contentUri)
            .thumbnail(0.33f)
            .centerCrop()
            .into(holder.imageView)
    }

    companion object {
        val imageDiffCallback = object : DiffUtil.ItemCallback<MediaStoreImage>() {
            override fun areItemsTheSame(oldItem: MediaStoreImage, newItem: MediaStoreImage) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: MediaStoreImage, newItem: MediaStoreImage) =
                oldItem == newItem
        }
    }

}

class ImageViewHolder(binding: ItemCreateGalleryBinding) :
    RecyclerView.ViewHolder(binding.root) {
    val imageView = binding.ivCreateGallery
}