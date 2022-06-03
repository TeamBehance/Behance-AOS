package com.sopt.behance_aos.model.response

import com.sopt.behance_aos.ui.base.BaseResponse

data class ResponseProject(
    val _id: String,
    val title: String,
    val photo: String, // 프로젝트 사진
    val writer: Writer
) : BaseResponse() {
    class Writer(
        val name: String,
        val photo: String // 유저 프로필 사진
    )
}