package com.example.numaboaterapia.appNavigation.pacient.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.numaboaterapia.R
import com.example.numaboaterapia.databinding.FragmentProfileBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.example.numaboaterapia.appNavigation.pacient.viewModel.GetFirebaseProfileDataViewModel
import com.example.numaboaterapia.appNavigation.pacient.views.MyDataActivity
import com.example.numaboaterapia.appNavigation.pacient.views.ShareDataActivity
import com.example.numaboaterapia.views.MainActivity


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private lateinit var viewModel: GetFirebaseProfileDataViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        viewModel = GetFirebaseProfileDataViewModel()
        setImage()
        setUpViews()
        return binding.root
    }

    private fun setImage() {
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
    }


}