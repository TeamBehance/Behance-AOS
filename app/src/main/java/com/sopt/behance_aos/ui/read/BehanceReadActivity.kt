package com.sopt.behance_aos.ui.read

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.sopt.behance_aos.R
import com.sopt.behance_aos.databinding.ActivityBehanceReadBinding
import java.util.Collections.addAll

class BehanceReadActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBehanceReadBinding
    private lateinit var viewPagerAdapter: BehanceReadViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBehanceReadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initAdapter()
        initBottomNavi()
    }

    private fun initAdapter() {
        val fragmentList = listOf(BehanceReadFragment())
        viewPagerAdapter = BehanceReadViewPagerAdapter(this)
        viewPagerAdapter.behanceReadFragments.addAll(fragmentList)

        binding.vpBehanceRead.adapter = viewPagerAdapter
    }

    private fun initBottomNavi() {
        binding.vpBehanceRead.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding.bnvBehanceReadBottomNavi.menu.getItem(position).isChecked = true
            }
        })

        binding.bnvBehanceReadBottomNavi.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_android_home -> {
                    binding.vpBehanceRead.setCurrentItem(FIRST_FRAGMENT, false)
                    return@setOnItemSelectedListener true
                }

                else -> {
                    return@setOnItemSelectedListener false
                }
            }
        }
    }

    companion object {
        const val FIRST_FRAGMENT = 0
        const val SECOND_FRAGMENT = 1
    }
}
