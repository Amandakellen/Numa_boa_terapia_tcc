package com.example.numaboaterapia.register.psychologist.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.numaboaterapia.R
import com.example.numaboaterapia.databinding.ActivityPsiBiographyBinding

class PsiBiography : AppCompatActivity() {
    private lateinit var binding : ActivityPsiBiographyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPsiBiographyBinding.inflate(layoutInflater)

        setUpToolBar()
        setContentView(binding.root)
    }

    fun setUpToolBar(){
        binding.psiBiographyToolbar.getBackButton().setOnClickListener {
            finish()
        }
        binding.psiBiographyToolbar.setStepText(R.string.psi_third_step)
        binding.psiBiographyToolbar.setTitleText(R.string.psi_third_title)
    }
}