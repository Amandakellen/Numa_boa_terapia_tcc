package com.example.numaboaterapia.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.numaboaterapia.R
import com.example.numaboaterapia.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)

        setupViews()

        setContentView(binding.root)
    }

    private fun setupViews(){

        binding.toolBarLogin.getBackButton().setOnClickListener {
            finish()
        }
    }
}