package com.sopt.behance_aos.ui.read

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.sopt.behance_aos.R
import com.sopt.behance_aos.databinding.FragmentBehanceReadBinding

class BehanceReadFragment : Fragment() {
    private var _binding: FragmentBehanceReadBinding? = null
    private val binding get() = _binding!!
    private lateinit var headAdapter: BehanceReadHeadAdapter
    private lateinit var bodyAdapter: BehanceReadBodyAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_behance_read, container, false)

        headAdapter = BehanceReadHeadAdapter()
        binding.rvBehanceReadHead.adapter = headAdapter

        headAdapter.headList = mutableListOf(
            BehanceReadHeadData(R.drawable.ellipse_1, "Behance"),
            BehanceReadHeadData(R.drawable.ellipse_2, "Adobe XD"),
            BehanceReadHeadData(R.drawable.ellipse_3, "비디오"),
            BehanceReadHeadData(R.drawable.ellipse_4, "#grape"),
            BehanceReadHeadData(R.drawable.ellipse_1, "Behance"),
            BehanceReadHeadData(R.drawable.ellipse_2, "Adobe XD"),
            BehanceReadHeadData(R.drawable.ellipse_3, "비디오"),
            BehanceReadHeadData(R.drawable.ellipse_4, "#grape")
        )

        bodyAdapter = BehanceReadBodyAdapter()
        binding.rvBehanceReadBody.adapter = bodyAdapter

        bodyAdapter.bodyList = mutableListOf(
            BehanceReadBodyData(R.drawable.rectangle_1, "LESJOUR!", "Margotleveque"),
            BehanceReadBodyData(R.drawable.rectangle_2, "LESJOUR!", "Margotleveque"),
            BehanceReadBodyData(R.drawable.rectangle_3, "LESJOUR!", "Margotleveque"),
            BehanceReadBodyData(R.drawable.rectangle_4, "LESJOUR!", "Margotleveque"),
            BehanceReadBodyData(R.drawable.rectangle_1, "LESJOUR!", "Margotleveque"),
            BehanceReadBodyData(R.drawable.rectangle_2, "LESJOUR!", "Margotleveque"),
            BehanceReadBodyData(R.drawable.rectangle_3, "LESJOUR!", "Margotleveque"),
            BehanceReadBodyData(R.drawable.rectangle_4, "LESJOUR!", "Margotleveque")
        )


        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}