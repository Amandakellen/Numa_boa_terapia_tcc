package com.example.numaboaterapia.appNavigation.psychologist.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.numaboaterapia.R
import com.example.numaboaterapia.appNavigation.pacient.views.bottomsheet.DeleteAccountBottomSheet
import com.example.numaboaterapia.appNavigation.psychologist.viewmodel.GetFirebasePsiMyDataViewModel
import com.example.numaboaterapia.databinding.ActivityPsiMyDataBinding

class PsiMyDataActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPsiMyDataBinding
    private lateinit var viewModel: GetFirebasePsiMyDataViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPsiMyDataBinding.inflate(layoutInflater)
        viewModel = GetFirebasePsiMyDataViewModel()

        setUpViews()
        setContentView(binding.root)
    }

    private fun setUpEditText() {
        val register = viewModel.getRegisterData()

        with(binding) {
            myDataPsiNameEditText.setText(register?.get(0) ?: " ")
            myDataPsiEmailEditText.setText(register?.get(1) ?: " ")
            myDataPsiPhoneEditText.setText(register?.get(2) ?: " ")
            myDataPsiWppEditText.setText(register?.get(3) ?: " ")
            myDataPsiTimeEditText.setText((register?.get(4) ?: " ")+ " minutos")
            myDataPsiEspecializationEditText.setText(register?.get(6) ?: " ")
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
                myDataPsiEspecializationEditText.isEnabled  = false
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
            binding.mydataPsiProgressbar.visibility = View.GONE
            binding.myDataPsiScrollView.visibility = View.VISIBLE
            setUpEditText()
        }

        binding.editUserButtonPsi.setText(R.string.edit_data_profile)

        binding.deleteAccountButtonPsi.setOnClickListener {
            DeleteAccountBottomSheet().show(supportFragmentManager, "DeleteAccountBottomSheet")
        }

        binding.editUserButtonPsi.setOnClickListener {
            val intent = Intent(this,EditMyDataPsiActivity::class.java)
            intent.putStringArrayListExtra("register",viewModel.getRegisterData() )

            startActivity(intent)
        }
    }
}