package com.satriyawicaksana.bfaasubmission.screen.userprofile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.satriyawicaksana.bfaasubmission.databinding.ActivityDetailProfileBinding

class DetailProfileActivity : AppCompatActivity() {
    private var _binding: ActivityDetailProfileBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DetailProfileViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}