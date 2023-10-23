package com.example.numaboaterapia.compose.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import com.example.numaboaterapia.Login.view.LoginActivity
import com.example.numaboaterapia.R
import com.example.numaboaterapia.databinding.ActivityMainBinding
import com.example.numaboaterapia.register.userType.view.UserType
import com.example.numaboaterapia.views.CustomView.BotaoArredondadoBranco

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val composeView = ComposeView(this)
        composeView.setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
    }

    private fun setupViews(button: BotaoArredondadoBranco, intent: Intent,text :Int) {

        button.setOnClickListener {
            startActivity(intent)
        }

        button.setText(text)
    }


}