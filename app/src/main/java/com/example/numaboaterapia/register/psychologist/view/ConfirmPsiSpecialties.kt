package com.example.numaboaterapia.register.psychologist.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.numaboaterapia.R
import com.example.numaboaterapia.databinding.ActivityConfirmPsiSpecialtiesBinding

class ConfirmPsiSpecialties : AppCompatActivity() {
    private lateinit var binding : ActivityConfirmPsiSpecialtiesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfirmPsiSpecialtiesBinding.inflate(layoutInflater)

        val itensSelected = getIntent().getExtras()?.getStringArrayList("specialties")
        setUpTollBar()

        setContentView(binding.root)
    }

    private fun setUpTollBar(){
        binding.confirmSpecialtiesToolBar.setTitleText(R.string.psi_confirm_specialties)
        binding.confirmSpecialtiesToolBar.setStepText(R.string.psi_second_step)
    }
}