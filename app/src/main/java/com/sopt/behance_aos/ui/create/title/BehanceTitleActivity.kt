package com.sopt.behance_aos.ui.create.title

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.sopt.behance_aos.R
import com.sopt.behance_aos.data.RetrofitBuilder
import com.sopt.behance_aos.databinding.ActivityBehanceTitleBinding
import com.sopt.behance_aos.ui.base.BaseActivity
import com.sopt.behance_aos.ui.create.model.request.RequestProject
import com.sopt.behance_aos.ui.create.model.response.ResponseProject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BehanceTitleActivity :
    BaseActivity<ActivityBehanceTitleBinding>(R.layout.activity_behance_title) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        postBtn()
    }

    private fun postBtn() {
        binding.tvCreatePost.setOnClickListener {
            postProject()
        }
    }

    private fun postProject() {
        val requestProject = RequestProject(
            title = binding.etvCreateTitle.text.toString(),
            photo = "https://behance10.s3.ap-northeast-2.amazonaws.com/1654256027949_%09s%0Fs%05u%EF%BF%BD%09c%EF%BF%BD%202022-06-02%20%0Bi%12n%2010.59.23.png",
            writer = "628fdb7fb376b6b9a1cefe6c"
        )

        val call = RetrofitBuilder.createService.postProject(requestProject)

        call.enqueue(object : Callback<ResponseProject> {
            override fun onResponse(
                call: Call<ResponseProject>,
                response: Response<ResponseProject>
            ) {
                Log.d("testing", response.toString())
                Log.d("testing", response.body().toString())

                if (response.isSuccessful) { // 파일 생성 성공

                    Toast.makeText(this@BehanceTitleActivity, "게시물이 업로드 되었습니다.", Toast.LENGTH_SHORT)
                        .show()
                    finish()
                }

            }

            override fun onFailure(call: Call<ResponseProject>, t: Throwable) { // 서버 통신에러
                Log.e("NetworkTest", "error:$t")
            }

        })

    }

}