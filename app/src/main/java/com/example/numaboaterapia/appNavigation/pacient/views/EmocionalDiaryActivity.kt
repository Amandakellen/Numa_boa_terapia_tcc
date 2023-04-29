package com.example.numaboaterapia.appNavigation.pacient.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.numaboaterapia.R
import com.example.numaboaterapia.databinding.ActivityEmocionalDiaryBinding

class EmocionalDiaryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEmocionalDiaryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityEmocionalDiaryBinding.inflate(layoutInflater)
        setUpviews()
        setContentView(binding.root)
    }

    private fun setUpviews(){
        binding.patientInformationToolBar.setTitleText(R.string.feelings)
        binding.patientInformationToolBar.getBackButton().setOnClickListener {
            finish()
        }
    }
}