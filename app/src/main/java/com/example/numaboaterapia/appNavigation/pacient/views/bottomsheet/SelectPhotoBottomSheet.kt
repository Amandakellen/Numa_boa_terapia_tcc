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
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.numaboaterapia.R
import com.example.numaboaterapia.camera.viewmodel.CameraViewModel
import com.example.numaboaterapia.databinding.SelectPhotoBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream

class SelectPhotoBottomSheet : BottomSheetDialogFragment() {

    private lateinit var binding: SelectPhotoBottomSheetBinding
    private lateinit var viewModel: CameraViewModel

    private val REQUEST_CAMERA_PERMISSION = 1
    private val REQUEST_IMAGE_CAPTURE = "image_capture"

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
            //dismiss()
        }
    }

    private val takePictureResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            dismiss()
            if (result.resultCode == RESULT_OK) {
                val data: Intent? = result.data
                val imageBitmap = data?.extras?.get("data") as Bitmap

                // Converter o Bitmap em um ByteArray
                val outputStream = ByteArrayOutputStream()
                imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
                val dataImage = outputStream.toByteArray()
                CoroutineScope(Dispatchers.IO).launch {
                    val resultado =
                        arguments?.getString("type")
                            ?.let { viewModel.sendToFirebase(dataImage, it) }
                    withContext(Dispatchers.Main) {
                        // Aqui você pode atualizar a UI com o resultado, por exemplo
                        if (resultado == "sucesso") {
//                            dismiss()
                            requireActivity().recreate()
                        } else {
                            // Ação para quando ocorrer um erro no upload
                        }
                    }
                }



            }
        }

    private fun takePicture() {
        if (ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.CAMERA),
                REQUEST_CAMERA_PERMISSION
            )
        } else {
            val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            takePictureResult.launch(takePictureIntent)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        if (requestCode == REQUEST_CAMERA_PERMISSION) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                takePicture()
            }
        }
    }

}
