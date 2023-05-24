package com.example.numaboaterapia.appNavigation.pacient.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.numaboaterapia.R
import com.example.numaboaterapia.databinding.ActivityPsiProfileInformationBinding

class PsiProfileInformation : AppCompatActivity() {
    private lateinit var binding: ActivityPsiProfileInformationBinding
    private lateinit var uId :String
    private lateinit var psiUser : ArrayList<HashMap<String, String>>
    private lateinit var psiBiographyData : ArrayList<HashMap<String, String>>
    private lateinit var psiSpecialtiesData : ArrayList<HashMap<String, String>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPsiProfileInformationBinding.inflate(layoutInflater)
        setUpViews()
        setContentView(binding.root)
    }

    private fun getData(){
        uId = getIntent().getStringExtra("uid").toString()
    }
    private fun setUpViews(){
         getData()
        binding.psiProfileInformationToolBar.getBackButton().setOnClickListener {
            finish()
        }
    }
}