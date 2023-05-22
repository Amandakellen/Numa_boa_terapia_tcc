package com.example.numaboaterapia.appNavigation.pacient.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.numaboaterapia.appNavigation.pacient.viewModel.PsiListViewModel
import com.example.numaboaterapia.appNavigation.pacient.views.adapter.DiaryAdapter
import com.example.numaboaterapia.appNavigation.pacient.views.adapter.PsiListAdapter
import com.example.numaboaterapia.databinding.FragmentPacientPsiListBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class PacientPsiList : Fragment() {
    private lateinit var binding: FragmentPacientPsiListBinding
    private lateinit var viewModel: PsiListViewModel
    private lateinit var psiUser : ArrayList<HashMap<String, String>>
    private lateinit var psiBiographyData : ArrayList<HashMap<String, String>>
    private val adapter = PsiListAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPacientPsiListBinding.inflate(layoutInflater,
            container, false)
        viewModel = PsiListViewModel()
        setUpViews()
        return binding.root
    }

    private fun setUpRecyclerView(){
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.getPsiUser()
            viewModel.getBiography()
            withContext(Dispatchers.Main) {
                binding.psiListProgressbar.visibility = View.GONE
                psiUser = viewModel.getPsiUserData()
                psiBiographyData = viewModel.getPsiBiographyData()

                adapter.psiUser = psiUser
                adapter.biography = psiBiographyData
                adapter.setOnClickListener(object : PsiListAdapter.onItemclickListener {
                    override fun onItemClick(position: Int) {


                    }
                })

            }
        }
    }


    private fun setUpViews(){
        binding.toolBarPsiList.getBackButton().visibility = View.GONE
        binding.psiListProgressbar.visibility = View.VISIBLE
        binding.psiListRecyclerview.layoutManager = LinearLayoutManager(context)
        binding.psiListRecyclerview.adapter = adapter
        setUpRecyclerView()

    }

}