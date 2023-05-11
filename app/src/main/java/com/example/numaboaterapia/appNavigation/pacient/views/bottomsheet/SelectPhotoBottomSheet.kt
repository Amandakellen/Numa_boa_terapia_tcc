package com.example.numaboaterapia.appNavigation.pacient.views.bottomsheet

import android.Manifest
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import com.example.numaboaterapia.R
import com.example.numaboaterapia.camera.viewmodel.CameraViewModel
import com.example.numaboaterapia.databinding.SelectPhotoBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class SelectPhotoBottomSheet : BottomSheetDialogFragment() {

    private lateinit var binding: SelectPhotoBottomSheetBinding
    private lateinit var viewModel:CameraViewModel

    private val REQUEST_CAMERA_PERMISSION = 1
    private val REQUEST_IMAGE_CAPTURE = 2

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = SelectPhotoBottomSheetBinding.inflate(inflater, container, false)
        viewModel = CameraViewModel()
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
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.CAMERA),
                REQUEST_CAMERA_PERMISSION)
        } else {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>,
                                            grantResults: IntArray) {
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                takePicture()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as Bitmap
            viewModel.bitmapToFile(imageBitmap,arguments?.getString("type"))
            val result = viewModel.sendToFirebase()
            result.invokeOnCompletion {

            }
        }
    }



    companion object {
        private const val REQUEST_IMAGE_CAPTURE = 1
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