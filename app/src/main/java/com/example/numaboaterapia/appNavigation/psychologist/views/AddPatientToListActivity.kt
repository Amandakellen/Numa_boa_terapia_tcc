package com.example.numaboaterapia.appNavigation.psychologist.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.doOnTextChanged
import com.example.numaboaterapia.R
import com.example.numaboaterapia.appNavigation.psychologist.viewmodel.SavePatientViewModel
import com.example.numaboaterapia.databinding.ActivityAddPatientToListBinding

class AddPatientToListActivity : AppCompatActivity() {
    private lateinit var binding:ActivityAddPatientToListBinding
    private lateinit var viewModel: SavePatientViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddPatientToListBinding.inflate(layoutInflater)
        viewModel = SavePatientViewModel()
        setUpViews()
        setUpEditText()
        setContentView(binding.root)
    }

    private fun setUpViews(){
        binding.addPatientToolBar.setTitleText(R.string.add_tool_bar)
        binding.addPatientToolBar.getBackButton().setOnClickListener {
            finish()
        }

        binding.savePatientButton.setText(R.string.add_patient)
    }

    private fun setUpEditText(){
        with(binding){
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