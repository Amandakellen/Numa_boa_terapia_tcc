package com.example.numaboaterapia.register.paciente.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.numaboaterapia.R
import com.example.numaboaterapia.databinding.ActivityPatientRegistrationDataBinding

class PatientRegistrationData : AppCompatActivity() {

    private lateinit var binding : ActivityPatientRegistrationDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding =  ActivityPatientRegistrationDataBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }
}