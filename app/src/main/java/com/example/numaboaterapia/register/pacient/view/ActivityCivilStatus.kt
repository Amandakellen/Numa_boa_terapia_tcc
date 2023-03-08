package com.example.numaboaterapia.register.pacient.view

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.numaboaterapia.R
import com.example.numaboaterapia.databinding.ActivityCivilStatusBinding
import com.example.numaboaterapia.register.pacient.view.adapters.PatientResponseAdapter
import com.example.numaboaterapia.register.pacient.viewModel.PatientCivilStatusViewModel

class ActivityCivilStatus : AppCompatActivity() {

    private lateinit var binding: ActivityCivilStatusBinding
    private var adapter: PatientResponseAdapter = PatientResponseAdapter()
    private lateinit var viewModel: PatientCivilStatusViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCivilStatusBinding.inflate(layoutInflater)

        viewModel = PatientCivilStatusViewModel()

        binding.civilStatusRecyclerview.layoutManager = GridLayoutManager(this, 2)
        binding.civilStatusRecyclerview.adapter = adapter

        adapter.data = viewModel.setDataItens()
        setUpViews()

        setContentView(binding.root)
    }

    private fun setUpViews() {
        binding.civilStatusToolBar.setTitleText(R.string.second_title)
        binding.civilStatusToolBar.setStepText(R.string.second_step)
        binding.civilStatusToolBar.getBackButton().setOnClickListener {
            finish()
        }

        adapter.setOnClickListener(object : PatientResponseAdapter.onItemclickListener {
            override fun onItemClick(position: Int) {
                val result = viewModel.saveValue(getString(adapter.data[position].feelingName))

                result.invokeOnCompletion {
                    if (result.getCompleted() != "Sucesso") {

                        Toast.makeText(
                            this@ActivityCivilStatus,
                            result.getCompleted(),
                            Toast.LENGTH_LONG
                        ).show()

                    } else {
                        startActivity(
                            Intent(this@ActivityCivilStatus,
                                ActivityFinancialSituation::class.java)
                        )
                    }

                }


            }
        })
    }
}