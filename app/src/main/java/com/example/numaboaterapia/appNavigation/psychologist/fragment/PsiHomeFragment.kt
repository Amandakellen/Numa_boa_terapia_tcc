package com.example.numaboaterapia.appNavigation.psychologist.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.numaboaterapia.R
import com.example.numaboaterapia.databinding.FragmentPsiHomeBinding


class PsiHomeFragment : Fragment() {
    private lateinit var binding: FragmentPsiHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPsiHomeBinding.inflate(inflater,container,false)
        return binding.root
    }


}