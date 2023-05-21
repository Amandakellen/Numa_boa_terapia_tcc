package com.example.numaboaterapia.appNavigation.pacient.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import com.example.numaboaterapia.R
import com.example.numaboaterapia.appNavigation.pacient.viewModel.PsiListViewModel
import com.example.numaboaterapia.databinding.FragmentPacientPsiListBinding
import com.example.numaboaterapia.register.psychologist.viewModel.PsiBiographyViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class PacientPsiList : Fragment() {
    private lateinit var binding: FragmentPacientPsiListBinding
    private lateinit var viewModel: PsiListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPacientPsiListBinding.inflate(layoutInflater,
            container, false)
        viewModel = PsiListViewModel()
        setUpViews()

        return binding.root
    }

    private fun setUpViews(){
        binding.toolBarPsiList.getBackButton().visibility = View.GONE
        binding.psiListProgressbar.visibility = View.VISIBLE

        CoroutineScope(Dispatchers.IO).launch {
            viewModel.getPsiList()
            withContext(Dispatchers.Main) {
                binding.psiListProgressbar.visibility = View.GONE
            }
        }
    }

}