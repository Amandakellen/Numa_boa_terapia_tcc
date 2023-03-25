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
        setContentView(binding.root)
    }

    private fun setUpViews(){
        binding.specialtiesButton.setText(R.string.next)
        binding.specialtiesCheckboxGroup.setViews(5)
        binding.psiSpecialtiesToolBar.getBackButton().setOnClickListener {
            finish()
        }

        binding.psiSpecialtiesToolBar.setStepText(R.string.psi_second_step)
        binding.psiSpecialtiesToolBar.setTitleText(R.string.psi_second_title)
    }
}