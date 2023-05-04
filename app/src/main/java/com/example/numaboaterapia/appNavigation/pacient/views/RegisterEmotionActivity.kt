package com.example.numaboaterapia.appNavigation.pacient.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import com.example.numaboaterapia.R
import com.example.numaboaterapia.appNavigation.pacient.viewModel.RegisterEmotionViewModel
import com.example.numaboaterapia.databinding.ActivityRegisterEmotionBinding

class RegisterEmotionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterEmotionBinding
    private lateinit var viewModel : RegisterEmotionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityRegisterEmotionBinding.inflate(layoutInflater)
        viewModel = RegisterEmotionViewModel()
        setUpViews()
        setDatas()
        setContentView(binding.root)
    }

    private fun setDatas(){
        getIntent().getStringExtra("itemSelected")?.let { viewModel.setFeeling(it) }
    }

    private fun setUpViews(){
        binding.patientDiaryRegisterToolBar.getBackButton().setOnClickListener {
            finish()
        }
        binding.patientDiaryRegisterToolBar.setTitleText(R.string.diary)

        binding.saveDiaryRegisterButton.setText(R.string.diary_button)

        binding.patientDiaryEditText.doOnTextChanged { text, start, before, count ->
            if(text.toString().length==850){
                setUpToast("Você atingiu a quantidade máxima de caracteres")
            }
            viewModel.setDiaryText(text.toString())
        }

        binding.saveDiaryRegisterButton.setOnClickListener {
            val result = viewModel.saveValue()
            result.invokeOnCompletion {
                if (result.getCompleted() != "Sucesso") {
                    setUpToast("Ocorreu um erro, tente novamente!")

                } else {

                    startActivity(Intent(this, HistoricActivity::class.java))
                }
            }


        }

    }


    private fun setUpToast(text : String){
        Toast.makeText(
            applicationContext,
            text, Toast.LENGTH_LONG
        ).show()
    }
}