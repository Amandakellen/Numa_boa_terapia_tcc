package com.example.numaboaterapia.appNavigation.pacient.views

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.numaboaterapia.R
import com.example.numaboaterapia.appNavigation.pacient.viewModel.PsiListViewModel
import com.example.numaboaterapia.appNavigation.pacient.viewModel.PsiProfileInformationViewModel
import com.example.numaboaterapia.databinding.ActivityPsiProfileInformationBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PsiProfileInformation : AppCompatActivity() {
    private lateinit var binding: ActivityPsiProfileInformationBinding
    private lateinit var viewModel: PsiProfileInformationViewModel
    private lateinit var uId :String
    private lateinit var psiUser : ArrayList< String>
    private lateinit var psiBiographyData : ArrayList<String>
    private lateinit var psiSpecialtiesData : ArrayList<String>
    private lateinit var psiImage : ByteArray
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPsiProfileInformationBinding.inflate(layoutInflater)
        viewModel = PsiProfileInformationViewModel()
        getData()
        setUpViews()
        setContentView(binding.root)
    }

    private fun getData(){
        uId = getIntent().getStringExtra("uid").toString()
        viewModel.getuId(uId)

    }

    private fun setUpViews(){
        binding.psiProfileInformationToolBar.getBackButton().setOnClickListener {
            finish()
        }
        CoroutineScope(Dispatchers.IO).launch {
            binding.psiProfileInformationProgress.visibility = View.VISIBLE
            binding.psiProfileInformationScrollview.visibility = View.GONE
            viewModel.getRegisterCollection()
            viewModel.getBiographyCollection()
            viewModel.getSpecialtiesCollection()
            viewModel.getImage()

            withContext(Dispatchers.Main) {
                psiUser = viewModel.getPsiUser()
                psiBiographyData = viewModel.getPsiBiography()
                psiSpecialtiesData = viewModel.getPsiSpecialties()
                psiImage = viewModel.getImageData()

                val bitmap = BitmapFactory.decodeByteArray(psiImage, 0, psiImage.size)


                with(binding){
                    psiProfileInformationName.text = psiUser[0]
                    if(bitmap==null){
                        psiProfileInformationPhoto.setImageResource(R.mipmap.psi_gray)
                    }else{
                        psiProfileInformationPhoto.setImageBitmap(bitmap)
                    }

                }
                binding.psiProfileInformationProgress.visibility = View.GONE
                binding.psiProfileInformationScrollview.visibility = View.VISIBLE

            }
        }
    }
}