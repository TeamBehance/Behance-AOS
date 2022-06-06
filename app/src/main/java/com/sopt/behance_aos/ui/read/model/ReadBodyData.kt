package com.sopt.behance_aos.ui.read.model

import com.sopt.behance_aos.R

data class ReadBodyData(
    val image: String,
    val title: String,
    val name: String,
    val profile: Int = R.drawable.ellipse_1
)
