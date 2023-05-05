package com.example.numaboaterapia.appNavigation.pacient.views.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.numaboaterapia.R
import com.example.numaboaterapia.databinding.SelectPhotoBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SelectPhotoBottomSheet: BottomSheetDialogFragment()  {

    private lateinit var binding : SelectPhotoBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = SelectPhotoBottomSheetBinding.
        inflate(inflater, container, false)

        setUpButtons()
        return binding.root
    }

    private fun setUpButtons(){
        binding.selectPhotoFromCamera.setText(R.string.take_picture)
    }
}