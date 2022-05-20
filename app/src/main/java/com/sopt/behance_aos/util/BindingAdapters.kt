package com.sopt.behance_aos.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("loadImage")
    fun setImg (view: ImageView,imageInt:Int) {
        view.setImageResource(imageInt)
    }
}