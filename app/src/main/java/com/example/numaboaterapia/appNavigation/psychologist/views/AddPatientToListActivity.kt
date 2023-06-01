package com.example.numaboaterapia.appNavigation.psychologist.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.example.numaboaterapia.R
import com.example.numaboaterapia.appNavigation.psychologist.viewmodel.SavePatientViewModel
import com.example.numaboaterapia.databinding.ActivityAddPatientToListBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AddPatientToListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddPatientToListBinding
    private lateinit var viewModel: SavePatientViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPatientToListBinding.inflate(layoutInflater)
        viewModel = SavePatientViewModel()
        setUpViews()
        setUpEditText()
        setContentView(binding.root)
    }

    private fun setUpViews() {
        binding.addPatientToolBar.setTitleText(R.string.add_tool_bar)
        binding.addPatientToolBar.getBackButton().setOnClickListener {
            finish()
        }

        binding.savePatientButton.setText(R.string.add_patient)

        binding.savePatientButton.setOnClickListener {

            CoroutineScope(Dispatchers.IO).launch {
                val patientExists = viewModel.verifyIfIsPacient()
                withContext(Dispatchers.Main) {
                    if(patientExists){
                        Toast.makeText(
                            applicationContext, "Paciente já foi cadastrado",
                            Toast.LENGTH_SHORT
                        ).show()
                    }else{
                        val result = viewModel.saveValue()
                        result.invokeOnCompletion {
                            if (result.getCompleted() != "Sucesso") {
                                Toast.makeText(
                                    applicationContext, "Ocorreu um erro, tente novamente!",
                                    Toast.LENGTH_SHORT
                                ).show()

                            } else {
                                finish()
                            }
                        }
                    }
                }
            }


        }

    }

    private fun setUpEditText() {
        val name = intent.getStringExtra("name")
        val email = intent.getStringExtra("email")
        val patientId = intent.getStringExtra("uId")

        viewModel.nameValue(name.toString())
        viewModel.emailValue(email.toString())
        viewModel.patientIdValue(patientId.toString())

        with(binding) {
            addPatientNameEditText.setText(name.toString())
            addPatientEmailEditText.setText(email.toString())

            addPatientContactWppEditText.doOnTextChanged { text, start, before, count ->
                viewModel.wppValue(text.toString())
            }
            addPatientCpfEditText.doOnTextChanged { text, start, before, count ->
                viewModel.cpfValue(text.toString())
            }
            addPatientGenderEditText.doOnTextChanged { text, start, before, count ->
                viewModel.genderValue(text.toString())
            }
            addPatientDateEditText.doOnTextChanged { text, start, before, count ->
                viewModel.dateValue(text.toString())
            }

            addPatientDateEditText.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                    // Não é necessário implementar este método
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    // Não é necessário implementar este método
                }

                override fun afterTextChanged(s: Editable?) {
                    val inputDateString = s.toString()

                    if (inputDateString.length == 2 || inputDateString.length == 5) {
                        // Adicione uma barra (/) automaticamente quando o usuário digitar o dia ou o mês
                        val formattedDateString = inputDateString + "/"
                        addPatientDateEditText.setText(formattedDateString)
                        addPatientDateEditText.setSelection(formattedDateString.length) // Mantém o cursor no final do texto
                        viewModel.dateValue(formattedDateString)
                    }

                    if (inputDateString.length > 10) {
                        // Limpe a entrada se o usuário digitar mais de 10 caracteres
                        addPatientDateEditText.setText(inputDateString.substring(0, 10))
                        addPatientDateEditText.setSelection(10) // Mantém o cursor no final do texto
                        viewModel.dateValue(inputDateString.substring(0, 10))
                    }
                }
            })

            addPatientProfessionEditText.doOnTextChanged { text, start, before, count ->
                viewModel.professionValue(text.toString())
            }

            addPatientSchoolingEditText.doOnTextChanged { text, start, before, count ->
                viewModel.schoolingValue(text.toString())
            }
            addPatientCountryEditText.doOnTextChanged { text, start, before, count ->
                viewModel.countryValue(text.toString())
            }
            addPatientValueEditText.doOnTextChanged { text, start, before, count ->
                viewModel.valueValue(text.toString())
            }

            observationEditText.doOnTextChanged { text, start, before, count ->
                viewModel.observationValue(text.toString())
            }

            addPatientAddressEditText.doOnTextChanged { text, start, before, count ->
                viewModel.addressValue(text.toString())
            }

            addPatientNumberEditText.doOnTextChanged { text, start, before, count ->
                viewModel.numberValue(text.toString())
            }
            addPatientNeighborhoodEditText.doOnTextChanged { text, start, before, count ->
                viewModel.neighborhoodValue(text.toString())
            }

            addPatientCityEditText.doOnTextChanged { text, start, before, count ->
                viewModel.cityValue(text.toString())
            }

            addPatientUfEditText.doOnTextChanged { text, start, before, count ->
                viewModel.ufValue(text.toString())
            }

            addPatientContactNameEditText.doOnTextChanged { text, start, before, count ->
                viewModel.contactNameValue(text.toString())
            }

            addPatientContactWppEditText.doOnTextChanged { text, start, before, count ->
                viewModel.contactWppValue(text.toString())
            }
        }


    }

}