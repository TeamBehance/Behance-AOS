package com.sopt.behance_aos.ui.read

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sopt.behance_aos.databinding.ActivityBehanceReadBinding

class BehanceReadActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBehanceReadBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBehanceReadBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}