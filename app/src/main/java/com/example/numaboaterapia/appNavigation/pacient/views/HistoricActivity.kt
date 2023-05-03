package com.example.numaboaterapia.appNavigation.pacient.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.numaboaterapia.R
import com.example.numaboaterapia.appNavigation.pacient.viewModel.EmocionalDiaryViewModel
import com.example.numaboaterapia.appNavigation.pacient.viewModel.RegisterEmotionViewModel
import com.example.numaboaterapia.databinding.ActivityHistoricBinding

class HistoricActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHistoricBinding
    private lateinit var viewModel: EmocionalDiaryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoricBinding.inflate(layoutInflater)
        viewModel = EmocionalDiaryViewModel()
        setUpRecyclerView()
        setUpViews()

        setContentView(binding.root)
    }

    private fun setUpViews() {
        binding.toolBarHistoric.getBackButton().setOnClickListener {
            startActivity(Intent(this, PacientApp::class.java))
        }
    }

    private fun setUpRecyclerView() {
        binding.progressHistoric.visibility = View.VISIBLE
        val result = viewModel.getCollection()
        result.invokeOnCompletion {
            binding.progressHistoric.visibility = View.GONE
        }
    }
}