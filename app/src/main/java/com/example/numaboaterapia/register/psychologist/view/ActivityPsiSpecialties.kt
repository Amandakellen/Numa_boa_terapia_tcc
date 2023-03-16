package com.example.numaboaterapia.register.psychologist.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.numaboaterapia.R
import com.example.numaboaterapia.databinding.ActivityPsiSpecialtiesBinding

class ActivityPsiSpecialties : AppCompatActivity() {
    private lateinit var binding: ActivityPsiSpecialtiesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPsiSpecialtiesBinding.inflate(layoutInflater)

        setUpViews()
        setContentView(binding.root)
    }

    private fun setUpViews(){
        binding.psiSpecialtiesToolBar.getBackButton().setOnClickListener {
            finish()
        }

        binding.psiSpecialtiesToolBar.setStepText(R.string.psi_second_step)
        binding.psiSpecialtiesToolBar.setTitleText(R.string.psi_second_title)
    }
}