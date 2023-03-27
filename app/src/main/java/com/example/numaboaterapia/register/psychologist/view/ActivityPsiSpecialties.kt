package com.example.numaboaterapia.register.psychologist.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.numaboaterapia.R
import com.example.numaboaterapia.databinding.ActivityPsiSpecialtiesBinding
import com.example.numaboaterapia.register.psychologist.viewModel.PsiSpecialtiesViewModel

class ActivityPsiSpecialties : AppCompatActivity() {
    private lateinit var binding: ActivityPsiSpecialtiesBinding
    private lateinit var viewModel: PsiSpecialtiesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPsiSpecialtiesBinding.inflate(layoutInflater)
        viewModel = PsiSpecialtiesViewModel()

        setUpViews()
        setUpCheckBoxes()
        setContentView(binding.root)
    }

    private fun setUpViews(){
        binding.specialtiesButton.setText(R.string.next)
        binding.psiSpecialtiesToolBar.getBackButton().setOnClickListener {
            finish()
        }

        binding.psiSpecialtiesToolBar.setStepText(R.string.psi_second_step)
        binding.psiSpecialtiesToolBar.setTitleText(R.string.psi_second_title)
    }

    private fun setUpCheckBoxes(){
        binding.specialtiesCheckboxGroup.setSpecialties(viewModel.getSpecialties())
        binding.specialtiesCheckboxGroup.setSpecialtiesViews()


    }
}