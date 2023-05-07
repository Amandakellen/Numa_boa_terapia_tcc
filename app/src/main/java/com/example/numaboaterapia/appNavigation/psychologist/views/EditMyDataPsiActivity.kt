package com.example.numaboaterapia.appNavigation.psychologist.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.core.widget.doOnTextChanged
import com.example.numaboaterapia.R
import com.example.numaboaterapia.appNavigation.psychologist.viewmodel.EditDataViewModel
import com.example.numaboaterapia.appNavigation.psychologist.viewmodel.GetFirebasePsiMyDataViewModel
import com.example.numaboaterapia.databinding.ActivityEditMyDataPsiBinding

class EditMyDataPsiActivity : AppCompatActivity() {
    private lateinit var myActivityResultLauncher: ActivityResultLauncher<Intent>

    private lateinit var binding: ActivityEditMyDataPsiBinding
    private lateinit var viewModel : EditDataViewModel
    private lateinit var myDataViewModel : GetFirebasePsiMyDataViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditMyDataPsiBinding.inflate(layoutInflater)
        viewModel = EditDataViewModel()
        myDataViewModel = GetFirebasePsiMyDataViewModel()
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

        binding.editMyDataUserButtonPsi.setOnClickListener {
            val result = viewModel.updateRegisterCollection()
            result.invokeOnCompletion {
                val intent = Intent(this, PsiMyDataActivity::class.java)
                myActivityResultLauncher.launch(intent)
            }
        }
    }

    private fun setDataValue() {
        binding.editMyDataProgressBar.visibility = View.VISIBLE
        binding.editMyDataPsiScrollView.visibility = View.GONE

        val result = myDataViewModel.getRegisterCollection()
        result.invokeOnCompletion {
            binding.editMyDataPsiScrollView.visibility = View.VISIBLE
            binding.editMyDataProgressBar.visibility = View.GONE
            var register = myDataViewModel.getRegisterData()

            register?.let { it1 -> viewModel.registerValue(it1) }

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

        with(binding) {
            editMyDataPsiEmailEditText.doOnTextChanged { text, start, before, count ->
                text?.toString()?.let { viewModel.emailValue(it) }
            }
            editMyDataPsiNameEditText.doOnTextChanged { text, start, before, count ->
                text?.toString()?.let { viewModel.nameValue(it) }
            }
            editMyDataPsiPhoneEditText.doOnTextChanged { text, start, before, count ->
                text?.toString()?.let { viewModel.phoneValue(it) }
            }
            editMyDataPsiWppEditText.doOnTextChanged { text, start, before, count ->
                text?.toString()?.let { viewModel.linkWppValue(it) }
            }
            editMyDataPsiTimeEditText.doOnTextChanged { text, start, before, count ->
                text?.toString()?.let { viewModel.timeValue(it) }
            }
            editMyDataPsiCrpEditText.doOnTextChanged { text, start, before, count ->
                text?.toString()?.let { viewModel.crpValue(it) }
            }
            editMyDataPsiEspecializationEditText.doOnTextChanged { text, start, before, count ->
                text?.toString()?.let { viewModel.especializacaoValue(it) }
            }

        }
    }
}