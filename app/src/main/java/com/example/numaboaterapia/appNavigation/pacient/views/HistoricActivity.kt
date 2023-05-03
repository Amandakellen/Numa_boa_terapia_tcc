package com.example.numaboaterapia.appNavigation.pacient.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.numaboaterapia.R
import com.example.numaboaterapia.appNavigation.pacient.viewModel.EmocionalDiaryViewModel
import com.example.numaboaterapia.appNavigation.pacient.viewModel.RegisterEmotionViewModel
import com.example.numaboaterapia.databinding.ActivityHistoricBinding

class HistoricActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHistoricBinding
    private lateinit var viewModel: RegisterEmotionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoricBinding.inflate(layoutInflater)
        viewModel = RegisterEmotionViewModel()
        setContentView(binding.root)
    }
}