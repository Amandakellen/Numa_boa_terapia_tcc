package com.example.numaboaterapia.appNavigation.psychologist.fragment

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.lifecycle.lifecycleScope
import com.example.numaboaterapia.R
import com.example.numaboaterapia.appNavigation.pacient.viewModel.GetFirebaseProfileDataViewModel
import com.example.numaboaterapia.appNavigation.pacient.views.bottomsheet.ChangeProfilephotoBottomSheet
import com.example.numaboaterapia.appNavigation.psychologist.viewmodel.GetFirebasePsiMyDataViewModel
import com.example.numaboaterapia.appNavigation.psychologist.views.MyProfilePsiActivity
import com.example.numaboaterapia.appNavigation.psychologist.views.PsiMyDataActivity
import com.example.numaboaterapia.camera.viewmodel.CameraViewModel
import com.example.numaboaterapia.databinding.FragmentPsiProfileBinding
import com.example.numaboaterapia.compose.main.MainActivity
import kotlinx.coroutines.launch

class PsiProfileFragment : Fragment() {
    private lateinit var myActivityResultLauncher: ActivityResultLauncher<Intent>
  private lateinit var binding : FragmentPsiProfileBinding
  private lateinit var loginViewModel: GetFirebaseProfileDataViewModel
  private lateinit var viewModel : GetFirebasePsiMyDataViewModel
  private lateinit var cameraViewModel: CameraViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentPsiProfileBinding.inflate(inflater,container,false)
        viewModel = GetFirebasePsiMyDataViewModel()
        loginViewModel = GetFirebaseProfileDataViewModel()
        cameraViewModel = CameraViewModel()

        setImage()
        setUpViews()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        setUpViews()
    }

    private fun setImage() {
        binding.progressBarFragmetProfilePsi.visibility = View.VISIBLE


        lifecycleScope.launch {
            cameraViewModel.getFirebaseFileImage("psi").await().collect { value ->
                when (value) {
                    "success" -> {
                        val bitArray = cameraViewModel.getImageByteArray()
                        val bitmap =
                            bitArray?.let {
                                BitmapFactory.decodeByteArray(bitArray, 0, it.size)
                            }
                        binding.profilePsiPhoto.setImageBitmap(bitmap)
                    }

                    else -> {
                        binding.profilePsiPhoto.setImageResource(R.mipmap.psi_gray)
                    }
                }
            }
            binding.progressBarFragmetProfilePsi.visibility = View.GONE
        }
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
            val bundle = Bundle()
            bundle.putString("type", "psi")
            val bottomSheet = ChangeProfilephotoBottomSheet()
            bottomSheet.arguments = bundle
            bottomSheet.show(parentFragmentManager, "changePhoto")
        }


    }

}