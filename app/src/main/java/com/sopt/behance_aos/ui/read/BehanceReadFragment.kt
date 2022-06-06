package com.sopt.behance_aos.ui.read

import android.os.Bundle
import android.util.Log
import android.view.View
import com.sopt.behance_aos.R
import com.sopt.behance_aos.data.RetrofitBuilder.readService
import com.sopt.behance_aos.data.response.ResponseProjectList
import com.sopt.behance_aos.data.response.ResponseStoryList
import com.sopt.behance_aos.databinding.FragmentBehanceReadBinding
import com.sopt.behance_aos.ui.base.BaseFragment
import com.sopt.behance_aos.ui.read.adapter.ReadHeaderAdapter
import com.sopt.behance_aos.ui.read.adapter.ReadMainAdapter
import com.sopt.behance_aos.ui.read.model.ReadBodyData
import com.sopt.behance_aos.ui.read.model.ReadHeadData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BehanceReadFragment :
    BaseFragment<FragmentBehanceReadBinding>(R.layout.fragment_behance_read) {

    private lateinit var readHeaderAdapter: ReadHeaderAdapter
    private lateinit var readMainAdapter: ReadMainAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tryGetReadList()
        // initAdapter()
    }

    private fun tryGetReadList() {

        // 전체 스토리 조회
        readHeaderAdapter = ReadHeaderAdapter()
        val callStory = readService.getStory()
        callStory.enqueue(object : Callback<ResponseStoryList> {
            override fun onResponse(
                call: Call<ResponseStoryList>,
                response: Response<ResponseStoryList>
            ) {
                val it = response.body()?.data
                if(response.isSuccessful){

                    val list = mutableListOf<ReadHeadData>()
                    if(it != null) {
                        for (i in it.indices) {
                            list.add(ReadHeadData(it[i].photo, it[i].name))
                        }
                        readHeaderAdapter.submitList(list)
                    }
                }
            }

            override fun onFailure(call: Call<ResponseStoryList>, t: Throwable) {
                Log.e("NetworkTest", "error:$t")
            }

        })



        // 전체 프로젝트 조회
        readMainAdapter = ReadMainAdapter(readHeaderAdapter)

        val callProject = readService.getProject()
        binding.rvRead.adapter = readMainAdapter
        callProject.enqueue(object : Callback<ResponseProjectList> {
            override fun onResponse(
                call: Call<ResponseProjectList>,
                response: Response<ResponseProjectList>
            ) {
                val it = response.body()?.data
                if(response.isSuccessful){

                    val list = mutableListOf<ReadBodyData>()
                    if (it != null) {
                        for (i in it.indices){
                            list.add(ReadBodyData(it[i].photo,it[i].title,it[i].writer.name))
                        }
                        readMainAdapter.submitList(list)
                    }
                }
            }

            override fun onFailure(call: Call<ResponseProjectList>, t: Throwable) {
                Log.e("NetworkTest", "error:$t")
            }

        })



    }

    /*private fun initAdapter() {
        readHeaderAdapter = ReadHeaderAdapter()
        /*readHeaderAdapter.submitList(
        listOf(
            ReadHeadData(R.drawable.ellipse_1, "Behance"),
            ReadHeadData(R.drawable.ellipse_2, "Adobe XD"),
            ReadHeadData(R.drawable.ellipse_3, "비디오"),
            ReadHeadData(R.drawable.ellipse_4, "#grape"),
            ReadHeadData(R.drawable.ellipse_1, "Behance"),
            ReadHeadData(R.drawable.ellipse_2, "Adobe XD"),
            ReadHeadData(R.drawable.ellipse_3, "비디오"),
            ReadHeadData(R.drawable.ellipse_4, "#grape")
        )
    )*/

        readMainAdapter = ReadMainAdapter(readHeaderAdapter)
        binding.rvRead.adapter = readMainAdapter
        /*readMainAdapter.submitList(
            listOf(
                ReadBodyData(R.drawable.rectangle_1, "LESJOUR!", "Margotleveque"),
                ReadBodyData(R.drawable.rectangle_2, "LESJOUR!", "Margotleveque"),
                ReadBodyData(R.drawable.rectangle_3, "LESJOUR!", "Margotleveque"),
                ReadBodyData(R.drawable.rectangle_4, "LESJOUR!", "Margotleveque"),
                ReadBodyData(R.drawable.rectangle_1, "LESJOUR!", "Margotleveque"),
                ReadBodyData(R.drawable.rectangle_2, "LESJOUR!", "Margotleveque"),
                ReadBodyData(R.drawable.rectangle_3, "LESJOUR!", "Margotleveque"),
                ReadBodyData(R.drawable.rectangle_4, "LESJOUR!", "Margotleveque")
            )
        )*/
    }*/
}
