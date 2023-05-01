package com.example.numaboaterapia.appNavigation.pacient.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.numaboaterapia.R
import com.example.numaboaterapia.appNavigation.pacient.viewModel.EmocionalDiaryViewModel
import com.example.numaboaterapia.appNavigation.pacient.views.EmocionalDiaryActivity
import com.example.numaboaterapia.databinding.FragmentPacientHomeBinding

class PacientHomeFragment : Fragment() {

    private lateinit var binding: FragmentPacientHomeBinding
    private lateinit var viewModel: EmocionalDiaryViewModel



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPacientHomeBinding.inflate(layoutInflater,container,false)
        viewModel = EmocionalDiaryViewModel()
        viewModel.getCollection()
        verifyIfDiaryIsEmpty()
        setUpViews()
        return binding.root
    }

    private fun verifyIfDiaryIsEmpty(){

        val result = viewModel.getCollection()
        result.invokeOnCompletion {
            if(viewModel.isPatiantDiaryEmpty()){
                binding.diarySubtitle.visibility = View.VISIBLE
                binding.diaryButton.text = getString(R.string.diary_first_button)
                binding.historyOfRecordsButton.visibility = View.GONE
            }else{
                binding.diarySubtitle.visibility = View.GONE
                binding.historyOfRecordsButton.visibility = View.VISIBLE
                binding.diaryButton.text = getString(R.string.diary_button_register)

            }
        }

    }

    private fun setUpViews(){
        binding.diaryButton.setOnClickListener {
            startActivity(Intent(activity, EmocionalDiaryActivity::class.java))
        }

        binding.historyOfRecordsButton.setText(R.string.diary_register)

    }

}



