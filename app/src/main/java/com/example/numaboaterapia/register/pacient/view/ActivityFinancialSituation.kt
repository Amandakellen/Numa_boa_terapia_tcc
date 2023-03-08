package com.example.numaboaterapia.register.pacient.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.numaboaterapia.R
import com.example.numaboaterapia.databinding.ActivityFinancialSituationBinding
import com.example.numaboaterapia.register.pacient.view.adapters.PatientResponseAdapter
import com.example.numaboaterapia.register.pacient.viewModel.PatientFinancialSituationViewmodel

class ActivityFinancialSituation : AppCompatActivity() {

    private lateinit var binding: ActivityFinancialSituationBinding
    private var adapter = PatientResponseAdapter()
    private lateinit var viewModel : PatientFinancialSituationViewmodel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFinancialSituationBinding.inflate(layoutInflater)

        viewModel = PatientFinancialSituationViewmodel()

        binding.financialSituationRecyclerview.layoutManager =
            GridLayoutManager(this, 2)
        binding.financialSituationRecyclerview.adapter = adapter
        adapter.data = viewModel.setDataItens()

        setUpViews()

        setContentView(binding.root)
    }

    private fun setUpViews() {
        binding.financialSituationToolBar.setStepText(R.string.third_step)
        binding.financialSituationToolBar.setTitleText(R.string.third_title)
        binding.financialSituationToolBar.getBackButton().setOnClickListener {
            finish()
        }

        adapter.setOnClickListener(object : PatientResponseAdapter.onItemclickListener {
            override fun onItemClick(position: Int) {
                val result = viewModel.saveValue(getString(adapter.data[position].feelingName))

                result.invokeOnCompletion {
                    if (result.getCompleted() != "Sucesso") {

                        Toast.makeText(
                            this@ActivityFinancialSituation,
                            result.getCompleted(),
                            Toast.LENGTH_LONG
                        ).show()

                    } else {
                       //todo
                    }

                }


            }
        })

    }
}