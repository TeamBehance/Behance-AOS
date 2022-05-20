package com.sopt.behance_aos.ui.read

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.sopt.behance_aos.R
import com.sopt.behance_aos.databinding.FragmentBehanceReadBinding
import com.sopt.behance_aos.ui.base.BaseFragment
import com.sopt.behance_aos.ui.read.model.ReadBodyData
import com.sopt.behance_aos.ui.read.model.ReadHeadData

class BehanceReadFragment : BaseFragment<FragmentBehanceReadBinding>(R.layout.fragment_behance_read) {

    private lateinit var readHeaderAdapter: ReadHeaderAdapter
    private lateinit var readMainAdapter: ReadMainAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
    }


    private fun initAdapter(){
        readHeaderAdapter = ReadHeaderAdapter()
        readHeaderAdapter.submitList(
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
        )

        readMainAdapter = ReadMainAdapter(readHeaderAdapter)
        binding.rvRead.adapter = readMainAdapter
        readMainAdapter.submitList(
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
        )
    }
}
