package com.sopt.behance_aos.data.response

data class ResponseStoryList (
    val status: Int,
    val success: Boolean,
    val message: String,
    val data:List<Data>
        ){
    data class Data(
        val _id:String,
        val name:String,
        val photo:String
    )
}