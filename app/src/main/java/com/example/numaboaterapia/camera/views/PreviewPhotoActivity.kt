package com.example.numaboaterapia.camera.views

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import com.example.numaboaterapia.R
import com.example.numaboaterapia.camera.viewmodel.CameraViewModel
import com.example.numaboaterapia.databinding.ActivityPreviewPhotoBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class PreviewPhotoActivity : AppCompatActivity() {

    lateinit var binding: ActivityPreviewPhotoBinding
    private lateinit var viewModel: CameraViewModel
    private val REQUEST_CAMERA_PERMISSION = 1
    private val REQUEST_IMAGE_CAPTURE = "image_capture"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPreviewPhotoBinding.inflate(layoutInflater)
        setUpViews()
        viewModel = CameraViewModel()
        setContentView(binding.root)
    }


    private fun setUpViews() {
        val dataImage = intent?.getByteArrayExtra("dataImage")
        val bitmap = BitmapFactory.decodeByteArray(dataImage, 0, dataImage?.size ?: 0)
        val type = intent.getStringExtra("type")
        val imageView = findViewById<ImageView>(R.id.photo_image_preview)
        val bitmapImage = BitmapFactory.decodeFile(bitmap.toString())
        imageView.setImageBitmap(bitmapImage)

        binding.confirmButton.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                val resultado =
                    dataImage?.let { it1 ->
                        if (type != null) {
                            viewModel.sendToFirebase(it1, type)
                        }
                    }
                withContext(Dispatchers.Main) {
                    // Aqui você pode atualizar a UI com o resultado, por exemplo
                    if (resultado?.equals("sucesso") == true) {
                        // Ação para quando o upload for bem-sucedido
                    } else {
                        Toast.makeText(
                            this@PreviewPhotoActivity,
                            "Ocorreu um erro, tente novamente!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }
    }
}