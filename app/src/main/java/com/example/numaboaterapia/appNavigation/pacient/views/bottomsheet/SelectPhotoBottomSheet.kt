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
import androidx.core.content.ContextCompat

import com.example.numaboaterapia.R
import com.example.numaboaterapia.databinding.SelectPhotoBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SelectPhotoBottomSheet : BottomSheetDialogFragment() {

    private lateinit var binding: SelectPhotoBottomSheetBinding
    private val REQUEST_CAMERA_PERMISSION = 1
    private val REQUEST_IMAGE_CAPTURE = 2

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
        binding.selectPhotoFromCamera.setOnClickListener {

            takePicture()
        }

    }

    private fun takePicture() {

        // Verifique se a permissão da câmera foi concedida pelo usuário
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            // Crie uma Intent para abrir a câmera
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

            // Verifique se existe um aplicativo de câmera disponível
            if (takePictureIntent.resolveActivity(requireActivity().getPackageManager()) != null) {
                // Inicie a atividade da câmera com startActivityForResult()
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        } else {
            // Solicite permissão de câmera ao usuário
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.CAMERA),
                REQUEST_CAMERA_PERMISSION
            )
        }

    }

//    private fun tirarPhoto() {
//
//        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//
//        val someActivityResultLauncher =
//            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//                if (result.resultCode == Activity.RESULT_OK) {
//                    // Handle the result.
//                }
//            }
//        someActivityResultLauncher.launch(intent)
//    }
}