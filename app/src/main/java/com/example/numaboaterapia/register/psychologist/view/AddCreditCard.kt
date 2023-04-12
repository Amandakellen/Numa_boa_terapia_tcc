package com.example.numaboaterapia.register.psychologist.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.numaboaterapia.R
import com.example.numaboaterapia.databinding.ActivityAddCreditCardBinding


class AddCreditCard : AppCompatActivity() {
    private lateinit var binding: ActivityAddCreditCardBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddCreditCardBinding.inflate(layoutInflater)
        setUpViews()
        setContentView(binding.root)
    }

    private fun setUpViews(){
        binding.toolBarCreditCard.getBackButton().setOnClickListener {
            finish()
        }
    }
}