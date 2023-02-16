package com.example.numaboaterapia.register.paciente.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class PatientData(
    val id: Int = 0,
    val patientName: String,
    val patientPhone: String,
    val patientCpf: String,
    val patientPassword: String
)
