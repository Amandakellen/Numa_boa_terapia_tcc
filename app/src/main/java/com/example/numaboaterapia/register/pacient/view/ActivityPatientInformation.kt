package com.example.numaboaterapia.register.pacient.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.numaboaterapia.databinding.ActivityPatientInformationBinding

class ActivityPatientInformation : AppCompatActivity() {
    private lateinit var binding: ActivityPatientInformationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPatientInformationBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

}