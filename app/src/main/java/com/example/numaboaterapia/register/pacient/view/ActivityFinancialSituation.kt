package com.example.numaboaterapia.register.pacient.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.numaboaterapia.R
import com.example.numaboaterapia.databinding.ActivityFinancialSituationBinding
import com.example.numaboaterapia.register.view.adapter.RegisterResponseAdapter
import com.example.numaboaterapia.register.pacient.viewModel.PatientFinancialSituationViewmodel

class ActivityFinancialSituation : AppCompatActivity() {

    private lateinit var binding: ActivityFinancialSituationBinding
    private var adapter = RegisterResponseAdapter()
    private lateinit var viewModel: PatientFinancialSituationViewmodel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityFinancialSituationBinding.inflate(layoutInflater)

        viewModel = PatientFinancialSituationViewmodel()

        binding.financialSituationRecyclerview.layoutManager =
            GridLayoutManager(this, 2)

        setUpRecyclerview()
        setUpViews()

        setContentView(binding.root)
    }

    fun setUpRecyclerview() {

        binding.financialSituationRecyclerview.layoutManager =
            GridLayoutManager(this, 2).also {
                it.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        return if (adapter.itemCount % 2 == 0) {
                            2
                        } else {
                            if (position != adapter.itemCount - 1) {
                                1
                            } else {
                                2
                            }
                        }
                    }

                }

            }

        binding.financialSituationRecyclerview.adapter = adapter
        adapter.data = viewModel.setDataItens()
    }

    private fun setUpViews() {
        binding.financialSituationToolBar.setStepText(R.string.third_step)
        binding.financialSituationToolBar.
        setTitleText(R.string.third_title)
        binding.financialSituationToolBar.getBackButton().setOnClickListener {
            finish()
        }

        adapter.setOnClickListener(object : RegisterResponseAdapter.onItemclickListener {
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
                        startActivity(
                            Intent(
                                this@ActivityFinancialSituation,
                                ActivityAverageIncome::class.java
                            )
                        )
                    }

                }


            }
        })

    }
}