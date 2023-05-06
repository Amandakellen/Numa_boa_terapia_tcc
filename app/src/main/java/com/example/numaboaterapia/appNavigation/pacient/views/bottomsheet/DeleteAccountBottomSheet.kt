package com.example.numaboaterapia.appNavigation.pacient.views.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.numaboaterapia.R
import com.example.numaboaterapia.appNavigation.pacient.viewModel.GetFirebaseProfileDataViewModel
import com.example.numaboaterapia.databinding.DeleteAccountBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DeleteAccountBottomSheet : BottomSheetDialogFragment() {

    private lateinit var binding: DeleteAccountBottomSheetBinding
    private lateinit var viewModel: GetFirebaseProfileDataViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DeleteAccountBottomSheetBinding.inflate(inflater, container, false)
        setUpButtons()

        return binding.root
    }

    private fun setUpButtons() {
        binding.deleteAccountBack.setOnClickListener {
            dismiss()
        }

        binding.deleteConfirmationButton.setText(R.string.yes)

        binding.deleteConfirmationButton.setOnClickListener {
            viewModel.deleteAccount()
            dismiss()
            DeleteAccountConfirmationBottomSheet().show(
                requireActivity().supportFragmentManager,
                "DeleteAccountConfirmationBottomSheet"
            )
        }
    }

}