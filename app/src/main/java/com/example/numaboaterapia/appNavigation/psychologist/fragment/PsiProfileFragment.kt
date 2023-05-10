package com.example.numaboaterapia.appNavigation.psychologist.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.numaboaterapia.R
import com.example.numaboaterapia.appNavigation.pacient.viewModel.GetFirebaseProfileDataViewModel
import com.example.numaboaterapia.appNavigation.pacient.views.bottomsheet.ChangeProfilephotoBottomSheet
import com.example.numaboaterapia.appNavigation.psychologist.data.MyActivityResultContract
import com.example.numaboaterapia.appNavigation.psychologist.data.MyResult
import com.example.numaboaterapia.appNavigation.psychologist.viewmodel.GetFirebasePsiMyDataViewModel
import com.example.numaboaterapia.appNavigation.psychologist.views.MyProfilePsiActivity
import com.example.numaboaterapia.appNavigation.psychologist.views.PsiMyDataActivity
import com.example.numaboaterapia.databinding.FragmentPsiProfileBinding
import com.example.numaboaterapia.views.MainActivity

class PsiProfileFragment : Fragment() {
    private lateinit var myActivityResultLauncher: ActivityResultLauncher<Intent>
  private lateinit var binding : FragmentPsiProfileBinding
  private lateinit var loginViewModel: GetFirebaseProfileDataViewModel
  private lateinit var viewModel : GetFirebasePsiMyDataViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentPsiProfileBinding.inflate(inflater,container,false)
        viewModel = GetFirebasePsiMyDataViewModel()
        loginViewModel = GetFirebaseProfileDataViewModel()

        setImage()
        setUpViews()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setUpViews()
    }

    private fun setImage() {
        Glide.with(this)
            .load(R.mipmap.psi_gray)
            .transform(CircleCrop())
            .into(binding.profilePsiPhoto)

    }

    private fun setUpViews(){
        binding.progressBarFragmetProfilePsi.visibility = View.VISIBLE

        binding.profilePsiButton.setText(R.string.logout)
        binding.profilePsiButton.setOnClickListener {
            loginViewModel.signOut()
            startActivity(Intent(activity, MainActivity::class.java))
        }

        val result = viewModel.getRegisterCollection()
        result.invokeOnCompletion {
            binding.progressBarFragmetProfilePsi.visibility = View.GONE
            val data = viewModel.getRegisterData()
            val name = data?.get(0) ?: " "
            val email = data?.get(1) ?: " "

            binding.psiProfileName.text = "Olá, " + name
            binding.psiProfileEmail.text = email
        }

        binding.myDataPsi.setOnClickListener {
            startActivity(Intent(activity, PsiMyDataActivity::class.java))
        }

        binding.myProfileDataPsi.setOnClickListener {
            startActivity(Intent(activity, PsiMyDataActivity::class.java))
        }


        binding.myProfilePsi.setOnClickListener {
            startActivity(Intent(activity, MyProfilePsiActivity::class.java))
        }

        binding.shareDataPsi.setOnClickListener {
            startActivity(Intent(activity, MyProfilePsiActivity::class.java))
        }


        binding.changePsiPhoto.setOnClickListener {
            ChangeProfilephotoBottomSheet().show(parentFragmentManager, "changePhoto")
        }


    }

}