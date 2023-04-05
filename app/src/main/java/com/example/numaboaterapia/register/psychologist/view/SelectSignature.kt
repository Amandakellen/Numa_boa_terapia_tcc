package com.example.numaboaterapia.register.psychologist.view

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.numaboaterapia.R
import com.example.numaboaterapia.databinding.ActivitySelectSignatureBinding

class SelectSignature : AppCompatActivity() {
    private lateinit var binding: ActivitySelectSignatureBinding
    private var signatureValue = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySelectSignatureBinding.inflate(layoutInflater)

        setUpViews()
        setContentView(binding.root)
    }

    private fun checkedChaneged(radioButton: RadioButton, signature : Int){
        val listRadioButton = listOf(binding.signatureRadioMounth,
        binding.signatureRadioYear, binding.signatureRadioSemester)

        val filter = listRadioButton.filterNot { it == radioButton }

        with(radioButton){
            if (!isSelected) {
                setChecked(true)
                setSelected(true)
                filter.forEach {
                    it.setChecked(false)
                    it.setSelected(false)
                }
                signatureValue = signature

            }else{
                setChecked(false)
                setSelected(false)

                signatureValue = 0
            }
        }
    }

    private fun setUpViews() {
        binding.toolBarSignature.getBackButton().setOnClickListener {
            finish()
        }
        binding.signatureButton.setText(R.string.signature_button)
        binding.signatureRadioYear.setOnClickListener{
            checkedChaneged(binding.signatureRadioYear, 620)
        }
        binding.signatureRadioMounth.setOnClickListener {
            checkedChaneged(binding.signatureRadioMounth, 60)
        }
        binding.signatureRadioSemester.setOnClickListener {
            checkedChaneged(binding.signatureRadioSemester, 280)
        }

        binding.signatureButton.setOnClickListener {
            if(signatureValue == 0){
                Toast.makeText(
                    applicationContext, "Selecione um plano",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}