package com.example.numaboaterapia.appNavigation.pacient.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Typeface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.numaboaterapia.R
import com.example.numaboaterapia.appNavigation.pacient.viewModel.PsiListViewModel
import com.example.numaboaterapia.appNavigation.pacient.views.PsiProfileInformation
import com.example.numaboaterapia.appNavigation.pacient.views.adapter.DiaryAdapter
import com.example.numaboaterapia.appNavigation.pacient.views.adapter.PsiListAdapter
import com.example.numaboaterapia.databinding.FragmentPacientPsiListBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.io.InputStream


class PacientPsiList : Fragment() {
    private lateinit var binding: FragmentPacientPsiListBinding
    private lateinit var viewModel: PsiListViewModel
    private lateinit var psiUser : ArrayList<HashMap<String, String>>
    private lateinit var psiBiographyData : ArrayList<HashMap<String, String>>
    private lateinit var psiSpecialtiesData : ArrayList<HashMap<String, String>>
    private var psiImage = mutableListOf<ByteArray>()
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
            viewModel.getSpecialties()
            viewModel.getImage()
            withContext(Dispatchers.Main) {
                binding.psiListProgressbar.visibility = View.GONE
                psiUser = viewModel.getPsiUserData()
                psiBiographyData = viewModel.getPsiBiographyData()
                psiSpecialtiesData = viewModel.getPsiSpecialtiesData()
                psiImage = viewModel.getImageData().toMutableList()

                adapter.psiUser = psiUser
                adapter.biography = psiBiographyData
                adapter.psiImage = psiImage
                adapter.setOnClickListener(object : PsiListAdapter.onItemclickListener {
                    override fun onItemClick(position: Int) {
                        var intent = Intent(requireContext(), PsiProfileInformation::class.java)
                        intent.putExtra("uid", adapter.biography[position]["uuid"])
                        startActivity(intent)
                    }
                })

            }
        }
    }


    @SuppressLint("ResourceType")
    private fun setUpViews(){
        binding.toolBarPsiList.getBackButton().visibility = View.GONE
        binding.psiListProgressbar.visibility = View.VISIBLE
        binding.psiListRecyclerview.layoutManager = LinearLayoutManager(context)
        val inputStream = context?.resources?.openRawResource(R.mipmap.psi_gray)
        val byteArray = inputStream?.let { fileToByteArray(it) }
        if (byteArray != null) {
            viewModel.setImageDefault(byteArray)
        }
        binding.psiListRecyclerview.adapter = adapter
        setUpRecyclerView()

    }

    fun fileToByteArray(inputStream: InputStream): ByteArray {
        val buffer = ByteArray(1024)
        val byteArrayOutputStream = ByteArrayOutputStream()

        var length: Int
        while (inputStream.read(buffer).also { length = it } != -1) {
            byteArrayOutputStream.write(buffer, 0, length)
        }

        inputStream.close()
        byteArrayOutputStream.flush()

        return byteArrayOutputStream.toByteArray()
    }

}