package com.example.numaboaterapia.register.userType.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.numaboaterapia.databinding.ActivityUserTypeBinding
import com.example.numaboaterapia.register.pacient.view.PatientRegistrationData
import com.example.numaboaterapia.register.psychologist.view.ActivityPsiRegistration
import com.example.numaboaterapia.register.psychologist.view.PsiBiography
import com.example.numaboaterapia.register.psychologist.view.SelectSignature


class UserType : AppCompatActivity() {

    private lateinit var binding: ActivityUserTypeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUserTypeBinding.inflate(layoutInflater)
        setUpViews()

        setContentView(binding.root)
    }

    private fun setUpViews() {
        binding.backButton.setOnClickListener {
            finish()
        }

        binding.clientImage.setOnClickListener {
            val intent = Intent(this, PatientRegistrationData::class.java)
            startActivity(intent)
        }

        binding.psiImage.setOnClickListener {
           //startActivity(Intent(this, ActivityPsiRegistration::class.java))
            startActivity(Intent(this, PsiBiography::class.java))
        }


    }
}