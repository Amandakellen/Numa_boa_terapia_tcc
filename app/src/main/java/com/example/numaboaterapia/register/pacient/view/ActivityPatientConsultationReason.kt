package com.example.numaboaterapia.register.pacient.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.numaboaterapia.R
import com.example.numaboaterapia.databinding.ActivityPatientConsultationReasonBinding
import com.example.numaboaterapia.databinding.AdapterPatientInformationListBinding
import com.example.numaboaterapia.register.pacient.view.adapters.ConsultationReasonAdapter
import com.example.numaboaterapia.register.pacient.viewModel.PatientConsultationReasonViewModel

class ActivityPatientConsultationReason : AppCompatActivity() {
    private lateinit var binding: ActivityPatientConsultationReasonBinding
    private lateinit var viewModel: PatientConsultationReasonViewModel
    private var adapter: ConsultationReasonAdapter = ConsultationReasonAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPatientConsultationReasonBinding.inflate(layoutInflater)
        setUpViews()

        val recyclerView = binding.reasonRecyclerView
        viewModel = PatientConsultationReasonViewModel()

        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = adapter
        adapter.data = viewModel.getDataItens()

        setContentView(binding.root)

    }

    private fun setUpViews() {
        binding.patientInformationToolBar.getBackButton().setOnClickListener {
            finish()
        }

        binding.patientInformationToolBar.setStepText(R.string.first_step)
        binding.patientInformationToolBar.setTitleText(R.string.first_title)

        adapter.setOnClickListener(object : ConsultationReasonAdapter.onItemclickListener {
            override fun onItemClick(position: Int) {
                val result = viewModel.saveValue(getString(adapter.data[position].feelingName))

                result.invokeOnCompletion {
                    if (result.getCompleted() != "Sucesso") {

                        Toast.makeText(
                            this@ActivityPatientConsultationReason,
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