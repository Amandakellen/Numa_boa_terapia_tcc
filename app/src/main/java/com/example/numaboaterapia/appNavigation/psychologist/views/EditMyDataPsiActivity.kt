package com.example.numaboaterapia.appNavigation.psychologist.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.doOnTextChanged
import com.example.numaboaterapia.R
import com.example.numaboaterapia.appNavigation.psychologist.viewmodel.EditDataViewModel
import com.example.numaboaterapia.databinding.ActivityEditMyDataPsiBinding

class EditMyDataPsiActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditMyDataPsiBinding
    private lateinit var viewModel : EditDataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditMyDataPsiBinding.inflate(layoutInflater)
        viewModel = EditDataViewModel()
        setUpViews()
        setDataValue()
        setUpEditText()
        setContentView(binding.root)
    }

    private fun setUpViews() {
        binding.editDataToolBar.setTitleText(R.string.edit_my_data)
        binding.editDataToolBar.getBackButton().setOnClickListener {
            finish()
        }

        binding.editMyDataUserButtonPsi.setText(R.string.save)
        binding.editMyDataCancelButtonPsi.setOnClickListener {
            finish()
        }
    }

    private fun setDataValue() {
        val register = getIntent().getStringArrayListExtra("register")


        with(binding) {
            editMyDataPsiNameEditText.setText(register?.get(0) ?: " ")
            editMyDataPsiEmailEditText.setText(register?.get(1) ?: " ")
            editMyDataPsiPhoneEditText.setText(register?.get(2) ?: " ")
            editMyDataPsiWppEditText.setText(register?.get(3) ?: " ")
            editMyDataPsiTimeEditText.setText((register?.get(4) ?: " "))
            editMyDataPsiEspecializationEditText.setText(register?.get(6) ?: " ")

        }
        enabledEditText()
    }

    private fun enabledEditText() {
        with(binding){
            editMyDataPsiNameEditText.isEnabled = true
            editMyDataPsiEmailEditText.isEnabled = true
            editMyDataPsiPhoneEditText.isEnabled = true
            editMyDataPsiWppEditText.isEnabled = true
            editMyDataPsiTimeEditText.isEnabled = true
            editMyDataPsiEspecializationEditText.isEnabled  = true
        }
    }

    private fun setUpEditText(){

        with(binding){
            editMyDataPsiEmailEditText.doOnTextChanged { text, start, before, count ->
                viewModel.emailValue(text.toString())
            }
            editMyDataPsiNameEditText.doOnTextChanged { text, start, before, count ->
                viewModel.nameValue(text.toString())
            }
            editMyDataPsiPhoneEditText.doOnTextChanged { text, start, before, count ->
                viewModel.phoneValue(text.toString())
            }
            editMyDataPsiWppEditText.doOnTextChanged { text, start, before, count ->
                viewModel.linkWppValue(text.toString())
            }
            editMyDataPsiTimeEditText.doOnTextChanged { text, start, before, count ->
                viewModel.timeValue(text.toString())
            }
            editMyDataPsiEspecializationEditText.doOnTextChanged { text, start, before, count ->
                viewModel.especializacaoValue(text.toString())
            }

        }

    }
}