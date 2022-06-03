package com.sopt.behance_aos.ui.base

open class BaseResponse(
    val status:Int=0,
    val success:Boolean=false,
    val message:String?=null
)