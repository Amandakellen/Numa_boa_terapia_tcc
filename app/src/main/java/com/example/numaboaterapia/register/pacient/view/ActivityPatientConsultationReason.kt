package com.example.numaboaterapia.register.pacient.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.numaboaterapia.R
import com.example.numaboaterapia.databinding.ActivityPatientConsultationReasonBinding
import com.example.numaboaterapia.register.pacient.view.adapters.ConsultationReasonAdapter
import com.example.numaboaterapia.register.pacient.viewModel.PatientConsultationReasonViewModel

class ActivityPatientConsultationReason : AppCompatActivity() {
    private lateinit var binding: ActivityPatientConsultationReasonBinding
    private lateinit var viewModel: PatientConsultationReasonViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPatientConsultationReasonBinding.inflate(layoutInflater)
        setUpViews()

        val recyclerView = binding.reasonRecyclerView
        viewModel = PatientConsultationReasonViewModel()

        val adapter = ConsultationReasonAdapter()

        recyclerView.adapter = adapter

//        adapter.data = viewModel.getDataItens()

        setContentView(binding.root)

    }

    private fun setUpViews(){
        binding.patientInformationToolBar.getBackButton().setOnClickListener {
            finish()
        }

        binding.patientInformationToolBar.setStepText(R.string.first_step)
        binding.patientInformationToolBar.setTitleText(R.string.first_title)


    }


}