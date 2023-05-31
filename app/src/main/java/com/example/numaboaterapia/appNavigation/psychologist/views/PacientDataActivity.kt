package com.example.numaboaterapia.appNavigation.psychologist.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import com.example.numaboaterapia.R
import com.example.numaboaterapia.appNavigation.psychologist.viewmodel.PatientDataViewModel
import com.example.numaboaterapia.databinding.ActivityPacientDataBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PacientDataActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPacientDataBinding
    private lateinit var viewModel: PatientDataViewModel
    private lateinit var usersData: ArrayList<HashMap<String, String>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPacientDataBinding.inflate(layoutInflater)
        viewModel = PatientDataViewModel()
        setUpViews()
        setContentView(binding.root)
    }

    private fun setUpViews() {
        binding.patientDataToolBar.setTitleText(R.string.patient_data_toll_bar)
        binding.patientDataProgressr.visibility = View.VISIBLE
        val patientId = intent.getStringExtra("patientId")
        CoroutineScope(Dispatchers.IO).launch {
            patientId?.let { viewModel.getPatientCollection(it) }
            withContext(Dispatchers.Main) {
                usersData = viewModel.getPatientData()
                binding.patientDataProgressr.visibility = View.GONE
                with(binding) {
                    patientDataNameEditText.setText(usersData[0].get("patient_name"))
                    patientDataEmailEditText.setText(usersData[0].get("patient_email"))
                    patientDataWppEditText.setText(usersData[0].get("patient_wpp"))
                    patientDataCpfEditText.setText(usersData[0].get("patient_cpf"))
                    patientDataGenderEditText.setText(usersData[0].get("patient_gender"))
                    patientDataDateEditText.setText(usersData[0].get("patient_birthday"))
                    patientDataProfessionEditText.setText(usersData[0].get("patient_profession"))
                    patientDataSchoolingEditText.setText(usersData[0].get("patient_schooling"))
                    patientDataCountryEditText.setText(usersData[0].get("patient_country"))
                    patientDataValueEditText.setText(usersData[0].get("patient_payment"))
                    patientDataEditText.setText(usersData[0].get("patient_observation"))
                    patientDataAddressEditText.setText(usersData[0].get("patient_address"))
                    patientDataNumberEditText.setText(usersData[0].get("patient_number"))
                    patientDataNeighborhoodEditText.setText(usersData[0].get("patient_neighborhood"))
                    patientDataCityEditText.setText(usersData[0].get("patient_city"))
                    patientDataUfEditText.setText(usersData[0].get("patient_uf"))
                    patientDataContactNameEditText.setText(usersData[0].get("patient_contact_name"))
                    patientDataContactWppEditText.setText(usersData[0].get("patient_contact_wpp"))

                }
            }
        }
    }



}