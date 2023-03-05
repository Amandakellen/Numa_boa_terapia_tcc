package com.example.numaboaterapia.views.CustomView

import android.content.Context
import android.os.Bundle
import android.os.PersistableBundle
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.numaboaterapia.R
import com.example.numaboaterapia.databinding.ToolBarPatientInformationReasonBinding

class PatientInformationReasonToolBar: AppCompatActivity(){

    private lateinit var binding : ToolBarPatientInformationReasonBinding

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

        binding = ToolBarPatientInformationReasonBinding.inflate(layoutInflater)

        setContentView(binding.root)

    }

}