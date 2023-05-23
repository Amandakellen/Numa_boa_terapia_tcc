package com.example.numaboaterapia.appNavigation.pacient.views.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.numaboaterapia.R
import com.example.numaboaterapia.databinding.ChangeProfilePhotoBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ChangeProfilephotoBottomSheet: BottomSheetDialogFragment()  {

    private lateinit var binding : ChangeProfilePhotoBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = ChangeProfilePhotoBottomSheetBinding.
        inflate(inflater, container, false)

        setUpButtons()
        return binding.root
    }

    private fun setUpButtons(){
        val type = arguments?.getString("type")

        binding.changePhotoBack.setOnClickListener {
            dismiss()
        }
        binding.changePhotoButton.setText(R.string.yes)

        binding.changePhotoButton.setOnClickListener {
            dismiss()

            val bundle = Bundle()
            bundle.putString("type", type)
            val bottomSheet = SelectPhotoBottomSheet()
            bottomSheet.arguments = bundle

            bottomSheet.show(
                requireActivity().supportFragmentManager,
                "SelectPhotoBottomSheet"
            )
        }
    }
}