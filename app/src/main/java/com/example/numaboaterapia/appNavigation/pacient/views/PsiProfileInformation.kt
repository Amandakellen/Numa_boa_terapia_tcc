package com.example.numaboaterapia.appNavigation.pacient.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.numaboaterapia.R
import com.example.numaboaterapia.databinding.ActivityPsiProfileInformationBinding

class PsiProfileInformation : AppCompatActivity() {
    private lateinit var binding: ActivityPsiProfileInformationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPsiProfileInformationBinding.inflate(layoutInflater)
        setUpViews()
        setContentView(binding.root)
    }


    private fun setUpViews(){
        binding.psiProfileInformationToolBar.getBackButton().setOnClickListener {
            finish()
        }
    }
}