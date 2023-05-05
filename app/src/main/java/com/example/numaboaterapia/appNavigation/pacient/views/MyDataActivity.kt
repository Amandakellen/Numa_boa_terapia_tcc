package com.example.numaboaterapia.appNavigation.pacient.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.numaboaterapia.R
import com.example.numaboaterapia.databinding.ActivityMyDataBinding

class MyDataActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMyDataBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyDataBinding.inflate(layoutInflater)
        setUpViews()

        setContentView(binding.root)
    }

    private fun setUpViews(){
        val email = getIntent().getStringExtra("email")
        val phone = getIntent().getStringExtra("phone")

        binding.infoDataEmailEditText.setText(email)
        binding.infoDataPhoneEditText.setText(phone)

        binding.infoDataEmailEditText.isEnabled = false
        binding.infoDataPhoneEditText.isEnabled = false

        binding.deleteAccountButton.setText(R.string.delete_account)

        binding.infoDataToolbar.getBackButton().setOnClickListener {
            finish()
        }


    }
}