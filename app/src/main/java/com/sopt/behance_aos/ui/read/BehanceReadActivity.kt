package com.sopt.behance_aos.ui.read

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.sopt.behance_aos.R
import com.sopt.behance_aos.databinding.ActivityBehanceReadBinding
import com.sopt.behance_aos.ui.create.BehanceCreateActivity
import com.sopt.behance_aos.ui.read.adapter.BehanceReadViewPagerAdapter


class BehanceReadActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBehanceReadBinding
    private lateinit var viewPagerAdapter: BehanceReadViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBehanceReadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initAdapter()
        initBottomNavi()
        restraintOverScrollMode()
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
                R.id.menu_android_my -> {
                    val intent = Intent(this,BehanceCreateActivity::class.java)
                    startActivity(intent)
                    return@setOnItemSelectedListener true
                }
                else -> {
                    return@setOnItemSelectedListener false
                }
            }
        }
    }

    private fun restraintOverScrollMode(){
        binding.vpBehanceRead.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
    }

    companion object {
        const val FIRST_FRAGMENT = 0
    }
}
