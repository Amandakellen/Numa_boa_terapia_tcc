package com.example.numaboaterapia.register.psychologist.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.example.numaboaterapia.R
import com.example.numaboaterapia.databinding.ActivityPsiBiographyBinding
import com.example.numaboaterapia.register.psychologist.viewModel.PsiBiographyViewModel

class PsiBiography : AppCompatActivity() {
    private lateinit var binding : ActivityPsiBiographyBinding
    private lateinit var viewModel : PsiBiographyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPsiBiographyBinding.inflate(layoutInflater)
        viewModel = PsiBiographyViewModel()

        setUpViews()
        setUpToolBar()
        setUpCheckBoxes()
        setContentView(binding.root)
    }

    private fun setUpViews(){
        binding.psiBiographyEditText.doOnTextChanged { text, start, before, count ->
            viewModel.setBiography(text.toString())
        }
        binding.psiCepEditText.doOnTextChanged { text, start, before, count ->
            viewModel.setCep(text.toString())
        }

        binding.biographyButton.setText(R.string.next)
        binding.cepButton.setText(R.string.send_cpf)

        binding.biographyButton.setOnClickListener {
            if(viewModel.isTypeOfServiceNullOrEmpty()){
                Toast.makeText(
                    applicationContext,
                    "Selecione ao menos um tipo de atendimento", Toast.LENGTH_LONG
                ).show()
            }else{
                //TODO
            }
        }

    }

    private fun setUpCheckBoxes(){
        binding.biographyOnineCheckbox.setOnCheckedChangeListener{ buttonView, isChecked->
            if(isChecked){
                viewModel.addTypeOfService(binding.biographyOnineCheckbox.text.toString())
            }else{
               viewModel.removeTypeOfService(binding.biographyOnineCheckbox.text.toString())
            }
        }
    }

    private fun setUpToolBar(){
        binding.psiBiographyToolbar.getBackButton().setOnClickListener {
            finish()
        }
        binding.psiBiographyToolbar.setStepText(R.string.psi_third_step)
        binding.psiBiographyToolbar.setTitleText(R.string.psi_third_title)
    }
}