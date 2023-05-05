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
import com.example.numaboaterapia.appNavigation.pacient.views.ShareDataActivity


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater,container,false)
        setImage()
        setUpViews()
        return binding.root
    }

    private fun setImage(){
        Glide.with(this)
            .load(R.mipmap.pacient_gray)
            .transform(CircleCrop())
            .into(binding.profilePacientPhoto)

    }

    private fun setUpViews(){
        binding.profilePatientButton.setText(R.string.logout)
        binding.shareDataPacient.setOnClickListener {
            startActivity(Intent(activity, ShareDataActivity::class.java))
        }
        binding.sharePatient.setOnClickListener {
            startActivity(Intent(activity, ShareDataActivity::class.java))
        }
    }


}