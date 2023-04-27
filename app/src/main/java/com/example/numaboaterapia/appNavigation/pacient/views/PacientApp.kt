package com.example.numaboaterapia.appNavigation.pacient.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.numaboaterapia.databinding.ActivityPacientAppViewBinding


class PacientApp : AppCompatActivity() {
    private lateinit var binding: ActivityPacientAppViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPacientAppViewBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}