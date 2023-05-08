package com.example.numaboaterapia.appNavigation.psychologist.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.numaboaterapia.R
import com.example.numaboaterapia.databinding.ActivityEditSpecialtiesBinding

class EditSpecialtiesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEditSpecialtiesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditSpecialtiesBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}