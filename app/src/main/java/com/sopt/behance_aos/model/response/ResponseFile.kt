package com.sopt.behance_aos.model.response

import com.sopt.behance_aos.ui.base.BaseResponse

data class ResponseFile(
    val data: Data
) : BaseResponse() {
    class Data(
        val _id: String,
        val link: String
    )
}