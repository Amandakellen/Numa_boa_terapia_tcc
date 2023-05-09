package com.example.numaboaterapia.register.psychologist.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.example.numaboaterapia.R
import com.example.numaboaterapia.databinding.ActivityPsiBiographyBinding
import com.example.numaboaterapia.register.psychologist.data.Cep
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

    private fun setUpToast(text : String){
        Toast.makeText(
            applicationContext,
            text, Toast.LENGTH_LONG
        ).show()
    }

    private fun checkIfItensIsEmptyOrNull(): String{
        if(viewModel.isTypeOfServiceNullOrEmpty() &&
            viewModel.isBiographyEmptyOrNull() &&
            viewModel.isCepEmptyOrNull() ){
            return "Preencha os campos solicitados"
        }else if(viewModel.isTypeOfServiceNullOrEmpty() && viewModel.isBiographyEmptyOrNull()){
            return "Digite sua biografia e selecione o tipo de atendimento"
        }else if(viewModel.isTypeOfServiceNullOrEmpty() && viewModel.isCepEmptyOrNull()){
            return "Informe o seu cep e selecione o tipo de atendimento"
        }else if(viewModel.isBiographyEmptyOrNull() && viewModel.isCepEmptyOrNull() ){
            return "Digite sua biografia e informe o seu cep"
        }else if(viewModel.isTypeOfServiceNullOrEmpty()){
            return "Selecione ao menos um tipo de atendimento"
        }else if(viewModel.isBiographyEmptyOrNull()){
            return "Digite uma biografia"
        }else if(viewModel.isCepEmptyOrNull()){
            return "Informe o seu cep"
        }

        return "ok"
    }

    private fun setUpViews(){
        binding.psiBiographyEditText.doOnTextChanged { text, start, before, count ->
            if(text.toString().length==850){
                setUpToast("Você atingiu a quantidade máxima de caracteres")
            }
            viewModel.setBiography(text.toString())
        }
        binding.psiCepEditText.doOnTextChanged { text, start, before, count ->
            viewModel.setCep(text.toString())
        }

        binding.biographyButton.setText(R.string.next)
        binding.cepButton.setText(R.string.send_cpf)
        binding.cepButton.setOnClickListener {
            if(viewModel.isCepEmptyOrNull()){
                setUpToast("Informe o seu cep")
            }else{
                viewModel.getLocation()
                val cidade = viewModel.getCepData()?.localidade
                val uf = viewModel.getCepData()?.uf

                if(!cidade.isNullOrEmpty()){
                    binding.psiCityEditText.setText(cidade.plus(" - ").plus(uf))
                    binding.cityTextInputLayout.visibility = View.VISIBLE
                }

            }
        }

        binding.biographyButton.setOnClickListener {
            val checkItens = checkIfItensIsEmptyOrNull()
            if(checkItens != "ok"){
                setUpToast(checkItens)
            }

            else{
                val result = viewModel.saveValue()
                result.invokeOnCompletion {
                    if (result.getCompleted() != "Sucesso") {
                        Toast.makeText(
                            applicationContext, "Ocorreu um erro, tente novamente!",
                            Toast.LENGTH_SHORT
                        ).show()

                    } else {
                        startActivity(Intent(this, SelectSignature::class.java))
                    }
                }
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

        binding.biopgraphyInPersonCheckbox.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                viewModel.addTypeOfService(binding.biopgraphyInPersonCheckbox.text.toString())
            }else{
                viewModel.removeTypeOfService(binding.biopgraphyInPersonCheckbox.text.toString())
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