package com.example.numaboaterapia.register.psychologist.view

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.example.numaboaterapia.R
import com.example.numaboaterapia.databinding.ActivityCreateMercadoPagoUserBinding
import com.example.numaboaterapia.register.psychologist.viewModel.CreateMercadoPagoUserViewModel


class CreateMercadoPagoUser : AppCompatActivity() {
    private lateinit var binding: ActivityCreateMercadoPagoUserBinding
    private lateinit var viewModel: CreateMercadoPagoUserViewModel
    private lateinit var paymentLink: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateMercadoPagoUserBinding.inflate(layoutInflater)
        viewModel = CreateMercadoPagoUserViewModel()
        setUpViews()
        setUpEditText()
        setUpSignature()

        setContentView(binding.root)
    }

    fun setUpEditText() {
        binding.textInputEditEmail.doOnTextChanged { text, start, before, count ->
            viewModel.emailValue(text.toString())
        }
        binding.textInputEditCpf.doOnTextChanged { text, start, before, count ->
            viewModel.getCPF(text.toString())
        }

        binding.textInputEditFirstName.doOnTextChanged { text, start, before, count ->
            viewModel.nameValue(text.toString())
        }
    }

    private fun setUpViews() {
        binding.toolBarCreditCard.getBackButton().setOnClickListener {
            finish()
        }

        binding.signatureButton.setText(R.string.payment_signature)
        binding.checkpayment.setText(R.string.next)

        binding.signatureButton.setOnClickListener {
            if(verifyEditText()){
                val result = viewModel.createUser()
                result.invokeOnCompletion {
                    if (result.getCompleted() != "Sucesso") {
                        setUpToast("Ocorreu um erro, tente novamente!")

                    } else {

                        val uri = Uri.parse(paymentLink);
                        val intent  =  Intent(Intent.ACTION_VIEW, uri);
                        binding.checkpayment.visibility = View.VISIBLE
                        startActivity(intent)
                    }
                }
            }

        }

        binding.checkpayment.setOnClickListener {
            viewModel.getPayment()
        }

    }

    private fun setUpToast(toastMessage: String) {
        Toast.makeText(
            applicationContext,
            toastMessage, Toast.LENGTH_LONG
        ).show()
    }

    private fun verifyEditText(): Boolean {
        if (viewModel.checkIfIsNullOrEmpty()) {
            setUpToast("Digite os dados!")
            return false
        } else {
            return true
        }
    }

    private fun setUpSignature() {
        val intent = getIntent().getIntExtra("signature", 60)

        when (intent) {
            620 -> {
                binding.signatureValue.setText(R.string.year_value)
                binding.signatureLabel.setText(R.string.year_label)
                paymentLink =
                    "https://www.mercadopago.com.br/" +
                            "subscriptions/checkout?" +
                            "preapproval_plan_id=2c938084876fa8960187728c051701ac"
            }

            280 -> {
                binding.signatureValue.setText(R.string.semester_value)
                binding.signatureLabel.setText(R.string.semester_label)
                paymentLink = "https://www.mercadopago.com.br/" +
                        "subscriptions/checkout?" +
                        "preapproval_plan_id=2c9380848770da650187728c6d890133"
            }

            else -> {
                binding.signatureValue.setText(R.string.mounth_value)
                binding.signatureLabel.setText(R.string.mounth_label)
                paymentLink = "https://www.mercadopago.com.br/" +
                        "subscriptions/checkout?" +
                        "preapproval_plan_id=2c938084876fdf0f0187728cc38401a3"
            }
        }

    }
}