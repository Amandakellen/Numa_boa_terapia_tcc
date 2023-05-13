package com.example.numaboaterapia.camera.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.numaboaterapia.R
import com.example.numaboaterapia.databinding.ActivityPreviewPhotoBinding

class PreviewPhotoActivity : AppCompatActivity() {

    lateinit var binding: ActivityPreviewPhotoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPreviewPhotoBinding.inflate(layoutInflater)
        setUpViews()

        setContentView(binding.root)
    }


    private fun setUpViews(){
            binding.confirmButton.setOnClickListener {

            }
    }
}