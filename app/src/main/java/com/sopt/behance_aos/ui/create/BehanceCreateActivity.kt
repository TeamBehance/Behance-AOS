package com.sopt.behance_aos.ui.create

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.sopt.behance_aos.R


class BehanceCreateActivity : AppCompatActivity() {

    private lateinit var binding:com.sopt.behance_aos.databinding.ActivityBehanceCreateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_behance_create)

    }

}