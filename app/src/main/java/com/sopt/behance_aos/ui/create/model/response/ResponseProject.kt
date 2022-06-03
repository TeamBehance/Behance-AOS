package com.sopt.behance_aos.ui.create.model.response

data class ResponseProject(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: Data,
) {
    data class Data(
        val _id: String,
        val title: String,
        val photo: String, // 프로젝트 사진
        val writer: Writer
    ){
        data class Writer(
            val name: String,
            val photo: String // 유저 프로필 사진
        )
    }

}