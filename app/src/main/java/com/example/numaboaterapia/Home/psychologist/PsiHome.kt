package com.example.numaboaterapia.Home.psychologist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.numaboaterapia.R
import com.example.numaboaterapia.databinding.ActivityPsiHomeBinding

class PsiHome : AppCompatActivity() {
    private lateinit var binding: ActivityPsiHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPsiHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}