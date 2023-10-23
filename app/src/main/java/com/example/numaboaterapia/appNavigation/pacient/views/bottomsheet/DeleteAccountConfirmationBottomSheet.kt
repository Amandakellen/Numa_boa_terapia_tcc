package com.example.numaboaterapia.appNavigation.pacient.views.bottomsheet

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.numaboaterapia.R
import com.example.numaboaterapia.databinding.DeleteAccountConfirmationBottomSheetBinding
import com.example.numaboaterapia.compose.main.MainActivity
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class DeleteAccountConfirmationBottomSheet : BottomSheetDialogFragment() {

    private lateinit var binding: DeleteAccountConfirmationBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DeleteAccountConfirmationBottomSheetBinding.
        inflate(inflater, container, false)
        setUpButtons()

        return binding.root
    }

    private fun setUpButtons(){
        binding.deleteOkButton.setText(R.string.ok)
        binding.deleteOkButton.setOnClickListener {

            startActivity(Intent(activity, MainActivity::class.java))
        }
    }
}