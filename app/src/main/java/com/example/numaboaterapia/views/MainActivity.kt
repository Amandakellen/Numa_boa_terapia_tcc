package com.example.numaboaterapia.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.numaboaterapia.Login.view.LoginActivity
import com.example.numaboaterapia.R
import com.example.numaboaterapia.databinding.ActivityMainBinding
import com.example.numaboaterapia.register.userType.view.UserType
import com.example.numaboaterapia.views.CustomView.BotaoArredondadoBranco

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setupViews(binding.botaoEntrar, Intent(this, LoginActivity::class.java), R.string.button_login)
        setupViews(binding.botaoCadastro, Intent(this, UserType::class.java),R.string.registration_button)

        setContentView(binding.root)
    }

    private fun setupViews(button: BotaoArredondadoBranco, intent: Intent,text :Int) {

        button.setOnClickListener {
            startActivity(intent)
        }

        button.setText(text)
    }


}