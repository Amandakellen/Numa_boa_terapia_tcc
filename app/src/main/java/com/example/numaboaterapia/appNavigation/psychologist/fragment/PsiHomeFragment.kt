package com.example.numaboaterapia.appNavigation.psychologist.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.numaboaterapia.R
import com.example.numaboaterapia.appNavigation.psychologist.views.ProfileAccesses
import com.example.numaboaterapia.databinding.FragmentPsiHomeBinding


class PsiHomeFragment : Fragment() {
    private lateinit var binding: FragmentPsiHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentPsiHomeBinding.inflate(inflater,container,false)
        setUpViews()

        return binding.root
    }

    private fun setUpViews(){
        binding.toolBarPsiHome.getBackButton().visibility = View.GONE
        binding.psiViewAccessHome.setOnClickListener {
            startActivity(Intent(requireContext(), ProfileAccesses::class.java))
        }
    }


}