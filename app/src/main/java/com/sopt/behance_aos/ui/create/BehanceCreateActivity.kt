package com.sopt.behance_aos.ui.create

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.sopt.behance_aos.R


class BehanceCreateActivity : AppCompatActivity() {

    private lateinit var binding:com.sopt.behance_aos.databinding.ActivityBehanceCreateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_behance_create)

    }
}