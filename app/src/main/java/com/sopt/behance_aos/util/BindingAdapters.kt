package com.sopt.behance_aos.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.sopt.behance_aos.R

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("loadImage")
    fun setImg (view: ImageView,imageInt:Int) {
        view.setImageResource(imageInt)
    }

    @JvmStatic
    @BindingAdapter("setCircleImage")
    fun setCircleImage(imageview: ImageView, drawable : Int?) {
        drawable?.let {
            Glide.with(imageview.context)
                .load(it)
                .circleCrop()
                .into(imageview)
        }
    }

    @JvmStatic
    @BindingAdapter("setCircleImageWithUrl")
    fun setCircleImageWithUrl(imageview: ImageView, url :String?) {
        url?.let {
            Glide.with(imageview.context)
                .load(it)
                .circleCrop()
                .into(imageview)
        }
    }

    @JvmStatic
    @BindingAdapter("setImage")
    fun setImage(imageview: ImageView, drawable : Int?) {
        drawable?.let {
            Glide.with(imageview.context)
                .load(it)
                .into(imageview)
        }
    }
}
