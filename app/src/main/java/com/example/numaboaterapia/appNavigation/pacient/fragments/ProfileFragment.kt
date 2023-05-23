package com.example.numaboaterapia.appNavigation.pacient.fragments

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.numaboaterapia.R
import com.example.numaboaterapia.databinding.FragmentProfileBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.example.numaboaterapia.appNavigation.pacient.viewModel.GetFirebaseProfileDataViewModel
import com.example.numaboaterapia.appNavigation.pacient.views.MyDataActivity
import com.example.numaboaterapia.appNavigation.pacient.views.ShareDataActivity
import com.example.numaboaterapia.appNavigation.pacient.views.bottomsheet.ChangeProfilephotoBottomSheet
import com.example.numaboaterapia.appNavigation.pacient.views.bottomsheet.DeleteAccountBottomSheet
import com.example.numaboaterapia.camera.viewmodel.CameraViewModel
import com.example.numaboaterapia.views.MainActivity
import kotlinx.coroutines.launch


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var viewModel: GetFirebaseProfileDataViewModel
    private lateinit var cameraViewModel: CameraViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        viewModel = GetFirebaseProfileDataViewModel()
        cameraViewModel = CameraViewModel()
        setImage()
        setUpViews()
        return binding.root
    }

    private fun setImage() {

        lifecycleScope.launch {
            cameraViewModel.getFirebaseFileImage("pacient").await().collect { value ->
                when (value) {
                    "success" -> {
                        val bitArray = cameraViewModel.getImageByteArray()
                        val bitmap =
                            bitArray?.let {
                                BitmapFactory.decodeByteArray(bitArray, 0, it.size) }
                        binding.profilePacientPhoto.setImageBitmap(bitmap)
                    }

                    else -> {
                        binding.profilePacientPhoto.setImageResource(R.mipmap.pacient_gray)
                    }
                }
            }
        }
        Glide.with(this)
            .load(R.mipmap.pacient_gray)
            .transform(CircleCrop())
            .into(binding.profilePacientPhoto)

    }

    private fun setUpViews() {
        binding.progressBarFragmetProfilePatient.visibility = View.VISIBLE

        binding.profilePatientButton.setText(R.string.logout)
        binding.shareDataPacient.setOnClickListener {
            startActivity(Intent(activity, ShareDataActivity::class.java))
        }
        binding.sharePatient.setOnClickListener {
            startActivity(Intent(activity, ShareDataActivity::class.java))
        }

        val result = viewModel.getCollection()
        result.invokeOnCompletion {
            binding.progressBarFragmetProfilePatient.visibility = View.GONE
            val data = viewModel.getProfileData()
            val name = data?.get(0) ?: " "
            val email = data?.get(1) ?: " "
            val phone = data?.get(2) ?: " "

            binding.patientProfileName.text = "Olá, " + name
            binding.patientProfileEmail.text = email

            binding.myProfileDataPacient.setOnClickListener {
                val intent = Intent(activity, MyDataActivity::class.java)
                intent.putExtra("email", email)
                intent.putExtra("phone", phone)

                startActivity(intent)
            }

            binding.myDataPatient.setOnClickListener {
                val intent = Intent(activity, MyDataActivity::class.java)
                intent.putExtra("email", email)
                intent.putExtra("phone", phone)

                startActivity(intent)
            }
        }

        binding.profilePatientButton.setOnClickListener {
            viewModel.signOut()
            startActivity(Intent(activity, MainActivity::class.java))
        }

        binding.changePatientPhoto.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("type", "pacient")
            val bottomSheet = ChangeProfilephotoBottomSheet()
            bottomSheet.arguments = bundle
            bottomSheet.show(parentFragmentManager, "changePhoto")
        }
    }


}