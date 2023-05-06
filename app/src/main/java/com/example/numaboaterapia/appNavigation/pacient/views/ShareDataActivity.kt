package com.example.numaboaterapia.appNavigation.pacient.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.numaboaterapia.databinding.ActivityShareDataBinding

class ShareDataActivity : AppCompatActivity() {

    private lateinit var binding: ActivityShareDataBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShareDataBinding.inflate(layoutInflater)
        setUpToolBar()
        setContentView(binding.root)
    }

    private fun setUpToolBar(){
        binding.shareDataToolbar.getBackButton().setOnClickListener {
            finish()
        }
    }
}