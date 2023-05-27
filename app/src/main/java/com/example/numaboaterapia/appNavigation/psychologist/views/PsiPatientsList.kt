package com.example.numaboaterapia.appNavigation.psychologist.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.numaboaterapia.R
import com.example.numaboaterapia.databinding.ActivityPsiPatientsListBinding

class PsiPatientsList : AppCompatActivity() {
    private lateinit var binding : ActivityPsiPatientsListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPsiPatientsListBinding.inflate(layoutInflater)
        setUpViews()
        setContentView(binding.root)
    }


    private fun setUpViews(){
        binding.psiPatientListToolBar.getBackButton().setOnClickListener {
            finish()
        }
        binding.psiPatientListToolBar.setTitleText(R.string.patient_list)
    }
}