package com.sopt.behance_aos.ui.read.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class BehanceReadViewPagerAdapter (fragmentActivity: FragmentActivity):
    FragmentStateAdapter(fragmentActivity)
{
    val behanceReadFragments = mutableListOf<Fragment>()

    override fun getItemCount(): Int = behanceReadFragments.size

    override fun createFragment(position: Int): Fragment = behanceReadFragments[position]
}
