package com.example.numaboaterapia.Login.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.numaboaterapia.R
import com.example.numaboaterapia.databinding.ActivityPaymentDeclinedBinding
import com.example.numaboaterapia.register.psychologist.view.SelectSignature

class PaymentDeclinedActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPaymentDeclinedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentDeclinedBinding.inflate(layoutInflater)
        setUpViews()
        setContentView(binding.root)
    }

    private fun setUpViews(){
        binding.toolBarDeclinePayment.getBackButton().visibility = View.GONE
        binding.signatureButton.setText(R.string.payment_signature)
        binding.signatureButton.setOnClickListener {
            startActivity(Intent(this, SelectSignature::class.java))
        }
    }
}