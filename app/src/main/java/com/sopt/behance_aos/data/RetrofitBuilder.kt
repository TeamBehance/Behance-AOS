package com.sopt.behance_aos.data


import com.sopt.behance_aos.ui.create.CreateService
import com.sopt.behance_aos.ui.read.ReadService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitBuilder {
    private const val BASE_URL = "http://13.124.227.66:5000/"

    private val okHttpClient = OkHttpClient.Builder()
        .connectTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .build()


    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // 이 밑에다가 이런식으로 서비스 객체 생성하기
    // val sampleService: SampleService = retrofit.create(SampleService::class.java)
    val createService: CreateService = retrofit.create(CreateService::class.java)
    val readService: ReadService = retrofit.create(ReadService::class.java)



}

