package com.example.numaboaterapia.appNavigation.pacient.views.bottomsheet

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat

import com.example.numaboaterapia.R
import com.example.numaboaterapia.databinding.SelectPhotoBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SelectPhotoBottomSheet : BottomSheetDialogFragment() {

    private lateinit var binding: SelectPhotoBottomSheetBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = SelectPhotoBottomSheetBinding.inflate(inflater, container, false)

        setUpButtons()
        return binding.root
    }

    private fun setUpButtons() {
        binding.selectPhotoFromCamera.setText(R.string.take_picture)

        binding.selectPhotoFromCamera.setOnClickListener {
            if (ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.CAMERA
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                activity?.let {
                    ActivityCompat.requestPermissions(
                        it,
                        arrayOf(Manifest.permission.CAMERA),
                        0
                    )
                }
            }
            tirarPhoto()
        }

    }

    private fun tirarPhoto() {

        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        val someActivityResultLauncher =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    // Handle the result.
                }
            }
        someActivityResultLauncher.launch(intent)
    }
}