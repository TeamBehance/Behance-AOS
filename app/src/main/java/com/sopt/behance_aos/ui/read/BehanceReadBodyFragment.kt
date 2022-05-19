package com.sopt.behance_aos.ui.read

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sopt.behance_aos.databinding.FragmentBehanceReadBodyBinding

class BehanceReadBodyFragment : Fragment() {
    private var _binding: FragmentBehanceReadBodyBinding? = null
    private val binding get() = _binding!!
    private lateinit var behanceReadBodyAdapter: BehanceReadBodyAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBehanceReadBodyBinding.inflate(layoutInflater, container, false)
        initAdapter()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initAdapter() {
        behanceReadBodyAdapter = BehanceReadBodyAdapter()
        binding.rvBehanceReadBody.adapter = behanceReadBodyAdapter

        behanceReadBodyAdapter.behanceReadBodyList.addAll(
            listOf(
                BehanceReadBodyData("YeongJu", "안드로이드 개발자"),
                BehanceReadBodyData("ChangHwan", "안드로이드 개발자"),
                BehanceReadBodyData("JiYeon", "안드로이드 개발자"),
                BehanceReadBodyData("Amanda", "안드로이드 개발자"),
                BehanceReadBodyData("Caredoso", "안드로이드 개발자"),
            )
        )
        behanceReadBodyAdapter.notifyDataSetChanged()
    }

}