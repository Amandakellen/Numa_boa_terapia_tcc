package com.example.numaboaterapia.appNavigation.psychologist.views

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.numaboaterapia.R
import com.example.numaboaterapia.databinding.ActivityPatientRecordBinding

class PatientRecord : AppCompatActivity() {
    private lateinit var binding:ActivityPatientRecordBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPatientRecordBinding.inflate(layoutInflater)
        setUpViews()
        setContentView(binding.root)
    }


    private fun setUpViews(){
        binding.pacientRecordToolBar.getBackButton().setOnClickListener {
            finish()
        }

        binding.pacientRecordToolBar.setTitleText(R.string.patient_record)

        val name = intent.getStringExtra("name")
        val cpf = intent.getStringExtra("cpf")
        val wpp = intent.getStringExtra("wpp")
        val image = intent.getByteArrayExtra("image")

        binding.patientRecordName.text = name
        binding.patientRecordCpf.text = cpf
        binding.patientRecordWpp.text = wpp

        val bitmap = BitmapFactory.decodeByteArray(image, 0, image?.size ?: 0)
        if (bitmap != null) {
            binding.patientRecordPhoto.setImageBitmap(bitmap)
        } else {
            binding.patientRecordPhoto.setImageResource(R.mipmap.pacient_gray)
        }

    }
}