package com.example.numaboaterapia.register.pacient.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.numaboaterapia.databinding.ActivityPatientConsultationReasonBinding
import com.example.numaboaterapia.register.pacient.view.adapters.ConsultationReasonAdapter
import com.example.numaboaterapia.register.pacient.viewModel.PatientConsultationReasonViewModel

class ActivityPatientConsultationReason : AppCompatActivity() {
    private lateinit var binding: ActivityPatientConsultationReasonBinding
    private lateinit var viewModel: PatientConsultationReasonViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPatientConsultationReasonBinding.inflate(layoutInflater)
        val recyclerView = binding.reasonRecyclerView
//        viewModel = PatientConsultationReasonViewModel()
//
//        val adapter = ConsultationReasonAdapter()
//
//        recyclerView.adapter = adapter
//
//        adapter.data = viewModel.getDataItens()

        setContentView(binding.root)

    }



}