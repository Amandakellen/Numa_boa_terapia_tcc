package com.example.numaboaterapia.appNavigation.pacient.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.numaboaterapia.R
import com.example.numaboaterapia.databinding.FragmentPacientPsiListBinding


class PacientPsiList : Fragment() {
    private lateinit var binding: FragmentPacientPsiListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPacientPsiListBinding.inflate(layoutInflater,
            container, false)
        return binding.root
    }


}