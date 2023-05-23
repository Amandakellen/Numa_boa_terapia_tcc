package com.example.numaboaterapia.appNavigation.pacient.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.numaboaterapia.R
import com.example.numaboaterapia.appNavigation.pacient.fragments.PacientPsiList

class PacientPsiListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pacient_psi_list)

        // Adicione o fragmento PacientPsiList à atividade
        supportFragmentManager.beginTransaction()
            .replace(R.id.pacient_psi_activity, PacientPsiList())
            .commit()
    }
}