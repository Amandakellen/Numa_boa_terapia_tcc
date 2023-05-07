package com.example.numaboaterapia.appNavigation.psychologist.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.core.widget.doOnTextChanged
import com.example.numaboaterapia.R
import com.example.numaboaterapia.appNavigation.psychologist.viewmodel.EditDataViewModel
import com.example.numaboaterapia.databinding.ActivityEditMyDataPsiBinding

class EditMyDataPsiActivity : AppCompatActivity() {
    private lateinit var myActivityResultLauncher: ActivityResultLauncher<Intent>

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
        binding.editMyDataProgressBar.visibility = View.GONE
        binding.editDataToolBar.setTitleText(R.string.edit_my_data)
        binding.editDataToolBar.getBackButton().setOnClickListener {
            finish()
        }

        binding.editMyDataUserButtonPsi.setText(R.string.save)
        binding.editMyDataCancelButtonPsi.setOnClickListener {
            finish()
        }

        binding.editMyDataUserButtonPsi.setOnClickListener {
            val result = viewModel.updateRegisterCollection()
            result.invokeOnCompletion {
                val intent = Intent(this, PsiMyDataActivity::class.java)
                myActivityResultLauncher.launch(intent)
            }
        }
    }

    private fun setDataValue() {
        val register = getIntent().getStringArrayListExtra("register") as ArrayList<String>

        viewModel.registerValue(register)

        with(binding) {
            editMyDataPsiNameEditText.setText(register?.get(0))
            editMyDataPsiEmailEditText.setText(register?.get(1))
            editMyDataPsiPhoneEditText.setText(register?.get(2))
            editMyDataPsiWppEditText.setText(register?.get(3))
            editMyDataPsiTimeEditText.setText((register?.get(4)))
            editMyDataPsiCrpEditText.setText(register?.get(5))
            editMyDataPsiEspecializationEditText.setText(register?.get(6))

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
            editMyDataPsiCrpEditText.isEnabled = true
            editMyDataPsiEspecializationEditText.isEnabled  = true
        }
    }

    private fun setUpEditText() {
        val register = getIntent().getStringArrayListExtra("register") as ArrayList<String>
        with(binding) {
            editMyDataPsiEmailEditText.doOnTextChanged { text, start, before, count ->
                viewModel.emailValue(text?.toString()?: register.get(1))
            }
            editMyDataPsiNameEditText.doOnTextChanged { text, start, before, count ->
                viewModel.nameValue(text?.toString()?: register.get(0))
            }
            editMyDataPsiPhoneEditText.doOnTextChanged { text, start, before, count ->
                viewModel.phoneValue(text?.toString()?: register.get(2))
            }
            editMyDataPsiWppEditText.doOnTextChanged { text, start, before, count ->
                viewModel.linkWppValue(text?.toString()?: register.get(3))
            }
            editMyDataPsiTimeEditText.doOnTextChanged { text, start, before, count ->
                viewModel.timeValue(text?.toString()?: register.get(4))
            }
            editMyDataPsiCrpEditText.doOnTextChanged { text, start, before, count ->
                viewModel.crpValue(text?.toString()?: register.get(5))
            }
            editMyDataPsiEspecializationEditText.doOnTextChanged { text, start, before, count ->
                viewModel.especializacaoValue(text?.toString()?: register.get(6))
            }

        }
    }
}