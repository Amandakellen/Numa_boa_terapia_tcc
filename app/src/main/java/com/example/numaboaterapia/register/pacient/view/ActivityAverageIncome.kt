package com.example.numaboaterapia.register.pacient.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import com.example.numaboaterapia.R
import com.example.numaboaterapia.databinding.ActivityAverageIncomeBinding
import com.example.numaboaterapia.register.pacient.viewModel.PatientAverageIconmeViewModel

class ActivityAverageIncome : AppCompatActivity() {
    private lateinit var binding: ActivityAverageIncomeBinding
    private lateinit var viewModel: PatientAverageIconmeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAverageIncomeBinding.inflate(layoutInflater)
        viewModel = PatientAverageIconmeViewModel()

        setUpViews()
        getRadionButtonValue()

        setContentView(binding.root)
    }

    fun getRadionButtonValue() {
        binding.averageIncomeRadioGroup.setOnCheckedChangeListener { radioGroup, checkedId ->
            viewModel.
            setAverageIncome(radioGroup.findViewById<RadioButton>(checkedId).text.toString())
            binding.averageIncomeButton.visibility = View.VISIBLE
        }
    }

    fun setUpViews() {
        binding.averageIncomeToolBar.getBackButton().setOnClickListener {
            finish()
        }
        binding.averageIncomeToolBar.setTitleText(R.string.fourth_title)
        binding.averageIncomeToolBar.setStepText(R.string.fourth_step)

        binding.averageIncomeButton.setOnClickListener {
            val message = viewModel.saveValue()
            message.invokeOnCompletion {
                if (message.getCompleted() != "Sucesso") {
                    Toast.makeText(
                        this@ActivityAverageIncome,
                        message.getCompleted(),
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    Toast.makeText(
                        this@ActivityAverageIncome,
                        "sucesso",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}