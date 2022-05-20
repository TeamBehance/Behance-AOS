package com.sopt.behance_aos.ui.create

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.sopt.behance_aos.R
import com.sopt.behance_aos.data.GalleryData
import com.sopt.behance_aos.ui.create.adpater.GalleryAdapter


class BehanceCreateActivity : AppCompatActivity() {

    private lateinit var binding: com.sopt.behance_aos.databinding.ActivityBehanceCreateBinding
    private lateinit var galleryAdapter: GalleryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_behance_create)
        initGalleryAdapter()
    }

    private fun initGalleryAdapter() {
        galleryAdapter = GalleryAdapter()
        val galleryList = mutableListOf<GalleryData>()

        galleryList.addAll(
            listOf(
                GalleryData(R.drawable.img_create_gallery1),
                GalleryData(R.drawable.img_create_gallery2),
                GalleryData(R.drawable.img_create_gallery3),
                GalleryData(R.drawable.img_create_gallery4),
                GalleryData(R.drawable.img_create_gallery5),
                GalleryData(R.drawable.img_create_gallery6),
                GalleryData(R.drawable.img_create_gallery7),
                GalleryData(R.drawable.img_create_gallery8),
                GalleryData(R.drawable.img_create_gallery9),
                GalleryData(R.drawable.img_create_gallery10),
                GalleryData(R.drawable.img_create_gallery11),
                GalleryData(R.drawable.img_create_gallery12),
            )
        )

        galleryAdapter.galleryList = galleryList

        binding.rvCreateGallery.adapter = galleryAdapter

        galleryAdapter.notifyDataSetChanged()
    }

}