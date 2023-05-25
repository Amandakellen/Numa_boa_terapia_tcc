package com.example.numaboaterapia.appNavigation.pacient.views

import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Typeface
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.numaboaterapia.R
import com.example.numaboaterapia.appNavigation.pacient.viewModel.PsiListViewModel
import com.example.numaboaterapia.appNavigation.pacient.viewModel.PsiProfileInformationViewModel
import com.example.numaboaterapia.databinding.ActivityPsiProfileInformationBinding
import com.google.android.material.chip.Chip
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PsiProfileInformation : AppCompatActivity() {
    private lateinit var binding: ActivityPsiProfileInformationBinding
    private lateinit var viewModel: PsiProfileInformationViewModel
    private lateinit var uId: String
    private lateinit var psiUser: ArrayList<String>
    private lateinit var psiBiographyData: ArrayList<String>
    private lateinit var psiSpecialtiesData: ArrayList<String>
    private lateinit var psiImage: ByteArray
    private val typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPsiProfileInformationBinding.inflate(layoutInflater)
        viewModel = PsiProfileInformationViewModel()
        getData()
        setUpData()
        setUpViews()
        setContentView(binding.root)
    }

    private fun getData() {
        uId = getIntent().getStringExtra("uid").toString()
        viewModel.getuId(uId)

    }

    private fun setUpData() {
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
                val buttonLabel = psiUser[0].split(" ")[0]


                with(binding) {
                    psiProfileInformationName.text = psiUser[0]
                    psiProfileInformationEspecializacao.text = psiUser[6]
                    psiProfileInformationCrp.text = psiUser[5]
                    psiProfileInformationLocation.text =
                        psiBiographyData[1] + " - " + psiBiographyData[2]
                    psiProfileInformationTime.text = "Duração:" + psiUser[4] + " minutos"
                    psiProfileInformationWpp.text = "Converse com " + buttonLabel
                    if (bitmap == null) {
                        psiProfileInformationPhoto.setImageResource(R.mipmap.psi_gray)
                    } else {
                        psiProfileInformationPhoto.setImageBitmap(bitmap)
                    }
                    psiSpecialtiesData.forEach {
                        Log.i("especialidades",it)
                        val chip = Chip(psiProfileInformationChipGroupSpecialties.context)
                        chip.text = it
                        chip.isClickable = false
                        chip.isCheckable = false
                        chip.setTextColor(
                            ContextCompat.getColor(
                                psiProfileInformationChipGroupSpecialties.context,
                                R.color.purple
                            )
                        )
                        chip.setChipBackgroundColorResource(R.color.white)
                        chip.setChipStrokeColorResource(R.color.light_purple)
                        chip.setChipStrokeWidth(2F)
                        chip.setTypeface(typeface)

                        binding.psiProfileInformationChipGroupSpecialties.addView(chip)

                    }

                    psiProfileInformationBiographyText.text = psiBiographyData[0]


                }
                binding.psiProfileInformationProgress.visibility = View.GONE
                binding.psiProfileInformationScrollview.visibility = View.VISIBLE

            }
        }
    }

    private fun openWhatsAppLink(link: String) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(link)

        try {
            startActivity(intent)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(
                this,
                "O WhatsApp não está instalado ou ocorreu um erro ao abrir o link.",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun setUpViews() {
        binding.psiProfileInformationToolBar.getBackButton().setOnClickListener {
            finish()
        }

        binding.psiProfileInformationWpp.setOnClickListener {
            val result = viewModel.savePacientData()
            result.invokeOnCompletion {
                val link = psiUser[3]
                openWhatsAppLink(link)
            }

        }

        binding.psiProfileInformationTime.setTypeface(typeface)


    }
}