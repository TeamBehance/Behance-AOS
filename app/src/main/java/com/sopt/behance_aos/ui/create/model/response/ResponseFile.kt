package com.sopt.behance_aos.ui.create.model.response

data class ResponseFile(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: Data
)  {
    data class Data(
        val _id: String,
        val link: String
    )
}