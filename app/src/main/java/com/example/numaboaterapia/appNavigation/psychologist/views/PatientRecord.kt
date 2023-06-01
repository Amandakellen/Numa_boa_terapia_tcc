package com.example.numaboaterapia.appNavigation.psychologist.views

import android.content.Intent
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
        val patientId = intent.getStringExtra("patientId")
        val image = intent.getByteArrayExtra("image")

        binding.patientRecordName.text = name
        binding.patientRecordCpf.text = "CPF: "+ cpf
        binding.patientRecordWpp.text = "Whatsapp: "+ wpp

        try{
            val bitmap = BitmapFactory.decodeByteArray(image, 0, image?.size ?: 0)
            if (bitmap != null) {
                binding.patientRecordPhoto.setImageBitmap(bitmap)
            } else {
                binding.patientRecordPhoto.setImageResource(R.mipmap.pacient_gray)
            }
        }catch(e: Exception){
            binding.patientRecordPhoto.setImageResource(R.mipmap.pacient_gray)
        }

        binding.patientData.setOnClickListener {
            val intent = Intent(this, PacientDataActivity::class.java)
            intent.putExtra("patientId",patientId)
            startActivity(intent)
        }


    }
}