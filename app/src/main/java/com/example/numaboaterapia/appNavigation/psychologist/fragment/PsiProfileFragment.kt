package com.example.numaboaterapia.appNavigation.psychologist.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.numaboaterapia.R
import com.example.numaboaterapia.databinding.FragmentPsiProfileBinding

class PsiProfileFragment : Fragment() {

  private lateinit var binding : FragmentPsiProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentPsiProfileBinding.inflate(inflater,container,false)
        return binding.root
    }

    private fun setImage() {
        Glide.with(this)
            .load(R.mipmap.psi_gray)
            .transform(CircleCrop())
            .into(binding.profilePsiPhoto)

    }

}