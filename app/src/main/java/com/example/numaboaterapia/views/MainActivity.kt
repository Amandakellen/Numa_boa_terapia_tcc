package com.example.numaboaterapia.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.numaboaterapia.databinding.ActivityMainBinding
import com.example.numaboaterapia.views.CustomView.BotaoArredondadoBranco

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setupViews(binding.botaoEntrar, Intent(this, LoginActivity::class.java))

        setContentView(binding.root)
    }

    private fun setupViews(button: BotaoArredondadoBranco, intent: Intent) {

        button.setOnClickListener {
            startActivity(intent)
        }
    }


}