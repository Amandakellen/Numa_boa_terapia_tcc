package com.example.numaboaterapia.register.pacient.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.numaboaterapia.R
import com.example.numaboaterapia.databinding.ActivityAverageIncomeBinding

class ActivityAverageIncome : AppCompatActivity() {
    private lateinit var binding : ActivityAverageIncomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAverageIncomeBinding.inflate(layoutInflater)

        setUpViews()

        setContentView(binding.root)
    }


    fun setUpViews(){
        binding.averageIncomeToolBar.getBackButton().setOnClickListener {
            finish()
        }
        binding.averageIncomeToolBar.setTitleText(R.string.fourth_title)
        binding.averageIncomeToolBar.setStepText(R.string.fourth_step)
    }
}