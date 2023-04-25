package com.example.numaboaterapia.register.psychologist.data.payment

import com.google.gson.annotations.SerializedName

data class FreeTrial (
    @SerializedName("frequency")
    val frequency: Int,
    @SerializedName("frequency_type")
    val frequencyType: String
)