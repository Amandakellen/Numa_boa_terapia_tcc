package com.example.numaboaterapia.register.userType.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.numaboaterapia.databinding.ActivityUserTypeBinding

class UserType : AppCompatActivity() {

    private lateinit var binding: ActivityUserTypeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivityUserTypeBinding.inflate(layoutInflater)

        setUpViews()

        setContentView(binding.root)
    }

    fun setUpViews(){

        binding.backButton.setOnClickListener {
            finish()
        }
    }
}