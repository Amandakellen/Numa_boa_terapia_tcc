package com.example.numaboaterapia.appNavigation.psychologist.viewmodel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.numaboaterapia.R
import com.example.numaboaterapia.databinding.ActivityEditPsiBiographyBinding

class EditPsiBiography : AppCompatActivity() {

    private lateinit var binding: ActivityEditPsiBiographyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditPsiBiographyBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }
}