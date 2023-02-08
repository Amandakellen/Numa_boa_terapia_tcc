package com.example.numaboaterapia.Login.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.numaboaterapia.R
import com.example.numaboaterapia.databinding.ActivityPasswordRecoveryMessageBinding

class PasswordRecoveryMessage : AppCompatActivity() {
    private lateinit var binding: ActivityPasswordRecoveryMessageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPasswordRecoveryMessageBinding.inflate(layoutInflater)

        setUp()

        setContentView(binding.root)
    }

    private fun setUp() {
        binding.buttonBackToLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.tryAgain.setOnClickListener {
            finish()
        }
    }
}