package com.example.numaboaterapia.register.psychologist.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.numaboaterapia.R
import com.example.numaboaterapia.databinding.ActivityCreateMercadoPagoUserBinding


class CreateMercadoPagoUser : AppCompatActivity() {
    private lateinit var binding: ActivityCreateMercadoPagoUserBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateMercadoPagoUserBinding.inflate(layoutInflater)
        setUpViews()
        setUpSignature()

        setContentView(binding.root)
    }

    private fun setUpViews(){
        binding.toolBarCreditCard.getBackButton().setOnClickListener {
            finish()
        }

        binding.signatureButton.setText(R.string.next)


    }

    private fun setUpSignature(){
        val intent = getIntent().getIntExtra("signature",60)

        when(intent){
            620 -> {
                binding.signatureValue.setText(R.string.year_value)
                binding.signatureLabel.setText(R.string.year_label)
            }
            280 ->{
                binding.signatureValue.setText(R.string.semester_value)
                binding.signatureLabel.setText(R.string.semester_label)
            }
            else->{
                binding.signatureValue.setText(R.string.mounth_value)
                binding.signatureLabel.setText(R.string.mounth_label)
            }
        }

    }
}