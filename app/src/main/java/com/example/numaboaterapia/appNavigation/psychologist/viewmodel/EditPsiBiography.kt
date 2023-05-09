package com.example.numaboaterapia.appNavigation.psychologist.viewmodel

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.lifecycleScope
import com.example.numaboaterapia.R
import com.example.numaboaterapia.appNavigation.psychologist.views.MyProfilePsiActivity
import com.example.numaboaterapia.databinding.ActivityEditPsiBiographyBinding
import com.example.numaboaterapia.register.psychologist.viewModel.PsiBiographyViewModel
import kotlinx.coroutines.launch

class EditPsiBiography : AppCompatActivity() {
    private lateinit var myActivityResultLauncher: ActivityResultLauncher<Intent>
    private lateinit var binding: ActivityEditPsiBiographyBinding
    private lateinit var viewModel: MyProfilePsiViewModel
    private lateinit var biographyViewModel : PsiBiographyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditPsiBiographyBinding.inflate(layoutInflater)
        viewModel = MyProfilePsiViewModel()
        biographyViewModel = PsiBiographyViewModel()
        setUpViews()
        lifecycleScope.launch {
            setData()
        }
        setContentView(binding.root)
    }

    private fun setUpToast(text : String){
        Toast.makeText(
            applicationContext,
            text, Toast.LENGTH_LONG
        ).show()
    }

    private fun checkIfItensIsEmptyOrNull(): String{
        if(biographyViewModel.isTypeOfServiceNullOrEmpty() &&
            biographyViewModel.isBiographyEmptyOrNull() ){
            return "Preencha os campos solicitados"
        }else if(biographyViewModel.isTypeOfServiceNullOrEmpty() && biographyViewModel.isBiographyEmptyOrNull()){
            return "Digite sua biografia e selecione o tipo de atendimento"
        }else if(biographyViewModel.isTypeOfServiceNullOrEmpty()){
            return "Selecione ao menos um tipo de atendimento"
        }else if(biographyViewModel.isBiographyEmptyOrNull()){
            return "Digite uma biografia"
        }
        return "ok"
    }
    private fun setUpViews() {
        binding.editBiographyPsiToolBar.getBackButton().setOnClickListener {
            finish()
        }
        binding.editBiographyPsiToolBar.setTitleText(R.string.edit_biography)

        binding.editCepButton.setText(R.string.cep)
        binding.editBiographySaveButton.setText(R.string.save)

        binding.editBiographyEditText.doOnTextChanged { text, start, before, count ->
            if(text.toString().length==850){
                setUpToast("Você atingiu a quantidade máxima de caracteres")
            }
            biographyViewModel.setBiography(text.toString())
        }
        binding.editCepEditText.doOnTextChanged { text, start, before, count ->
            biographyViewModel.setCep(text.toString())
        }

        binding.editCepButton.setText(R.string.send_cpf)
        binding.editCepButton.setOnClickListener {
            if(biographyViewModel.isCepEmptyOrNull()){
                setUpToast("Informe o seu cep")
            }else{
                biographyViewModel.getLocation()
                val cidade = biographyViewModel.getCepData()?.localidade
                val uf = biographyViewModel.getCepData()?.uf

                if(!cidade.isNullOrEmpty()){
                    binding.editPsiCityEditText.setText(cidade.plus(" - ").plus(uf))
                }

            }
        }

        binding.editBiographySaveButton.setOnClickListener {
            val checkItens = checkIfItensIsEmptyOrNull()
            if(checkItens != "ok"){
                setUpToast(checkItens)
            }else{
                val locality = binding.editPsiCityEditText.text?.split(" - ")
                val biography = biographyViewModel.getBiography()
                val typeOfService = biographyViewModel.getTypeOfService()
                val city = locality?.get(0)
                val uf = locality?.get(1)

                val result = viewModel.updateBiographyCollection(biography,city,uf,typeOfService)
                result.invokeOnCompletion {
                    val intent = Intent(this, MyProfilePsiActivity::class.java)
                       myActivityResultLauncher.launch(intent)
                }

            }
        }

    }

    private fun isChecked(array: ArrayList<String>, text: String): Boolean =
        array.contains(text)

    fun checkIfIsChecked(checkBox : CheckBox){
        if (checkBox.isChecked){
            biographyViewModel.addTypeOfService(checkBox.text.toString())
        }

    }
    private suspend fun setData() {
        binding.editBiographyScrollview.visibility = View.GONE
        binding.editBiographyProgressbar.visibility = View.VISIBLE

        val result = viewModel.getBiographyCollection()
        result.await()
            binding.editBiographyScrollview.visibility = View.VISIBLE
            binding.editBiographyProgressbar.visibility = View.GONE
            val biography = viewModel.getBiographyData()

            binding.editBiographyEditText.setText(biography?.get(0))
            binding.editPsiCityEditText.setText(biography?.get(1) + " - " + biography?.get(2))
            if(biography?.get(3)?.length==2){
                binding.editBiographyOnineCheckbox.isChecked =
                    isChecked(biography?.get(3)?.split(",")
                            as ArrayList<String>, "Atendo online")
                binding.editBiopgraphyInPersonCheckbox.isChecked =
                    isChecked(biography?.get(3)!!.split(",")
                            as ArrayList<String>, "Atendo presencial")
            }else{
                if (biography?.get(3)?.contains("Atendo online") == true){
                    binding.editBiographyOnineCheckbox.isChecked = true
                }else{
                    binding.editBiopgraphyInPersonCheckbox.isChecked = true
                }
            }


            checkIfIsChecked(binding.editBiographyOnineCheckbox)
            checkIfIsChecked(binding.editBiopgraphyInPersonCheckbox)
        }

}