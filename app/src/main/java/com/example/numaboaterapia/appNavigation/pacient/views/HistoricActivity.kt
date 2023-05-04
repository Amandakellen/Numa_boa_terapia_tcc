package com.example.numaboaterapia.appNavigation.pacient.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.numaboaterapia.appNavigation.pacient.viewModel.EmocionalDiaryViewModel
import com.example.numaboaterapia.appNavigation.pacient.views.adapter.HistoricAdapter
import com.example.numaboaterapia.databinding.ActivityHistoricBinding

class HistoricActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHistoricBinding
    private lateinit var viewModel: EmocionalDiaryViewModel
    private lateinit var adapter :HistoricAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoricBinding.inflate(layoutInflater)
        viewModel = EmocionalDiaryViewModel()
        adapter = HistoricAdapter(this)
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

            binding.historicReyclerview.layoutManager = LinearLayoutManager(this)
            binding.historicReyclerview.adapter = adapter
            adapter.dataFeelings = viewModel.getFeelingList()
            adapter.dataDate = viewModel.getDateList()
            adapter.dataText = viewModel.getTextList()
        }

    }
}