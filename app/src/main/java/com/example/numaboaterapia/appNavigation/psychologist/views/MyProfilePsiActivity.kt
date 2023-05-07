package com.example.numaboaterapia.appNavigation.psychologist.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.numaboaterapia.R
import com.example.numaboaterapia.appNavigation.psychologist.fragment.PsiProfileFragment
import com.example.numaboaterapia.appNavigation.psychologist.viewmodel.MyProfilePsiViewModel
import com.example.numaboaterapia.databinding.ActivityMyProfilePsiBinding

class MyProfilePsiActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMyProfilePsiBinding
    private lateinit var viewModel: MyProfilePsiViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMyProfilePsiBinding.inflate(layoutInflater)
        viewModel = MyProfilePsiViewModel()
        setUpData()
        setUpViews()
        setUpProfileImage()
        setContentView(binding.root)
    }

    private fun setUpViews() {
        binding.myProfilePsiToolBar.getBackButton().setOnClickListener {
            finish()
        }
        binding.myProfilePsiToolBar.setTitleText(R.string.my_profile)
        binding.myProfileEditRegister.setOnClickListener {
            startActivity(Intent(this, PsiMyDataActivity::class.java))
        }
    }

    private fun setUpProfileImage() {
        Glide.with(this)
            .load(R.mipmap.psi_gray)
            .transform(CircleCrop())
            .into(binding.myprofilePsiPhoto)
    }

    private fun setUpData() {
        binding.myPsiProfileScrollview.visibility = View.GONE
        binding.myProfilePsiProgressBar.visibility = View.VISIBLE

        val register = viewModel.getRegisterCollection()
        register.invokeOnCompletion {
            val registerData = viewModel.getRegisterData()

            with(binding) {
                myProfilePsiName.text = registerData?.get(0)
                myProfilePsiProfileEspecializacao.text = registerData?.get(6)
                myProfilePsiProfileCrp.text = registerData?.get(5)
                myProfileTime.text = "Duração: "+ registerData?.get(4) + "minutos"
            }

            val biography = viewModel.getBiographyCollection()
            biography.invokeOnCompletion {
                val biographyData = viewModel.getBiographyData()
                with(binding) {
                    myProfileBiography.text = biographyData?.get(0)
                    myProfilePsiProfileLocation.text =
                        biographyData?.get(1) + " - " + biographyData?.get(2)
                }

                val specialties = viewModel.getSpecialtiesCollection()
                specialties.invokeOnCompletion {
                    viewModel.getSpecialtiesData()?.let { it1 -> setUpCheckBox(it1) }
                    binding.myPsiProfileScrollview.visibility = View.VISIBLE
                    binding.myProfilePsiProgressBar.visibility = View.GONE

                }
            }
        }
    }

    private fun setUpCheckBox(array : ArrayList<String>){

    }
}