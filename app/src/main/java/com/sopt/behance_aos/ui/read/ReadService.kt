package com.sopt.behance_aos.ui.read

import com.sopt.behance_aos.data.response.ResponseProjectList
import com.sopt.behance_aos.data.response.ResponseStoryList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface ReadService{

    // 프로젝트 전제조회
    @GET("/project")
    fun getProject(
    ): Call<ResponseProjectList>

    // 프로젝트 스토리 조회
    @GET("/story")
    fun getStory(
    ): Call<ResponseStoryList>

}