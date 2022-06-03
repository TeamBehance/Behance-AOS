package com.sopt.behance_aos.ui.create

import com.sopt.behance_aos.model.request.RequestProject
import com.sopt.behance_aos.model.response.ResponseFile
import com.sopt.behance_aos.model.response.ResponseProject
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface CreateService {

    // 파일 업로드
    @Multipart
    @POST("file/upload")
    fun postFile(
        @Part file: MultipartBody.Part
    ): Call<ResponseFile>

    // 프로젝트 생성
    @POST("project/android")
    fun postProject(
        @Body requestProject: RequestProject
    ): Call<ResponseProject>

}