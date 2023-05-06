package com.example.numaboaterapia.appNavigation.psychologist.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.numaboaterapia.R
import com.example.numaboaterapia.appNavigation.pacient.viewModel.GetFirebaseProfileDataViewModel
import com.example.numaboaterapia.appNavigation.pacient.views.bottomsheet.DeleteAccountBottomSheet
import com.example.numaboaterapia.appNavigation.psychologist.viewmodel.GetFirebasePsiProfileViewModel
import com.example.numaboaterapia.databinding.ActivityPsiMyDataBinding

class PsiMyDataActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPsiMyDataBinding
    private lateinit var viewModel: GetFirebasePsiProfileViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPsiMyDataBinding.inflate(layoutInflater)
        viewModel = GetFirebasePsiProfileViewModel()

        setUpViews()
        setContentView(binding.root)
    }

    private fun containString(array: ArrayList<String>, text: String): String {
        if (array.contains(text)) {
            return "Sim"
        }

        return "Nã0"
    }

    private fun setUpEditText() {
        val register = viewModel.getRegisterData()
        val biography = viewModel.getBiographyData()

        val typeOfService = biography?.get(3)?.split(",") ?: " "



        with(binding) {
            myDataPsiNameEditText.setText(register?.get(0) ?: " ")
            myDataPsiEmailEditText.setText(register?.get(1) ?: " ")
            myDataPsiPhoneEditText.setText(register?.get(2) ?: " ")
            myDataPsiWppEditText.setText(register?.get(3) ?: " ")
            myDataPsiTimeEditText.setText((register?.get(4) ?: " ")+ " minutos")
            myDataPsiInPersonEditText.setText(
                containString(
                    typeOfService as ArrayList<String>,
                    "Atendo presencial"
                )
            )
            myDataPsiOnlineEditText.setText(
                containString(
                    typeOfService as ArrayList<String>,
                    "Atendo online"
                )
            )
            myDataPsiEspecializationEditText.setText(register?.get(6) ?: " ")
            myDataPsiCityEditText.setText(biography?.get(1) ?: " ")
            myDataPsiEstateEditText.setText(biography?.get(2) ?: " ")
        }
        disableEditText()
    }

    private fun disableEditText() {
            with(binding){
                myDataPsiNameEditText.isEnabled = false
                myDataPsiEmailEditText.isEnabled = false
                myDataPsiPhoneEditText.isEnabled = false
                myDataPsiWppEditText.isEnabled = false
                myDataPsiTimeEditText.isEnabled = false
                myDataPsiInPersonEditText.isEnabled = false
                myDataPsiOnlineEditText.isEnabled = false
                myDataPsiEspecializationEditText.isEnabled  = false
                myDataPsiCityEditText.isEnabled  = false
                myDataPsiEstateEditText.isEnabled = false
            }
    }

    private fun setUpViews() {
        binding.myDataPsiToolbar.getBackButton().setOnClickListener {
            finish()
        }
        binding.myDataPsiScrollView.visibility = View.GONE
        binding.mydataPsiProgressbar.visibility = View.VISIBLE

        val result = viewModel.getRegisterCollection()
        result.invokeOnCompletion {
            val biography = viewModel.getBiographyCollection()
            biography.invokeOnCompletion {
                binding.mydataPsiProgressbar.visibility = View.GONE
                binding.myDataPsiScrollView.visibility = View.VISIBLE
                setUpEditText()
            }
        }

        binding.editUserButtonPsi.setText(R.string.edit_data_profile)

        binding.deleteAccountButtonPsi.setOnClickListener {
            DeleteAccountBottomSheet().show(supportFragmentManager, "DeleteAccountBottomSheet")
        }
    }
}