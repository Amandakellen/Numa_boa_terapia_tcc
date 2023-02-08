package com.example.numaboaterapia.register.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_type_table")
data class RegisterData(@PrimaryKey @ColumnInfo(name = "user_type") val userType: String)

