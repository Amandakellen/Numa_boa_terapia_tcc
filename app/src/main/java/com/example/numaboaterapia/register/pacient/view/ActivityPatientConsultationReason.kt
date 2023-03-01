package com.example.numaboaterapia.register.pacient.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.numaboaterapia.databinding.ActivityPatientConsultationReasonBinding

class ActivityPatientConsultationReason : AppCompatActivity() {
    private lateinit var binding: ActivityPatientConsultationReasonBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPatientConsultationReasonBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
    


}