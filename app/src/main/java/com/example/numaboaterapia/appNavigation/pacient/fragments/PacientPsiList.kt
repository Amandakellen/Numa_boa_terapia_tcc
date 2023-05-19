package com.example.numaboaterapia.appNavigation.pacient.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import com.example.numaboaterapia.R
import com.example.numaboaterapia.databinding.FragmentPacientPsiListBinding
import com.example.numaboaterapia.register.psychologist.viewModel.PsiBiographyViewModel


class PacientPsiList : Fragment() {
    private lateinit var binding: FragmentPacientPsiListBinding
    private lateinit var viewModel: PsiBiographyViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPacientPsiListBinding.inflate(layoutInflater,
            container, false)
        viewModel = PsiBiographyViewModel()
        setUpViews()

        return binding.root
    }

    private fun setUpViews(){
        binding.toolBarPsiList.getBackButton().visibility = View.GONE
    }

}