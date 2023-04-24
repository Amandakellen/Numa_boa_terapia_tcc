package com.example.numaboaterapia.register.psychologist.data.payment

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Summarized (
    @SerializedName("quotas")
    val quotas: Int,
    @SerializedName("charged_quantity")
    val chargedQuantity: Int,
    @SerializedName("charged_amount")
    val chargedAmount: Double,
    @SerializedName("pending_charge_quantity")
    val pendingChargeQuantity: Int,
    @SerializedName("pending_charge_amount")
    val pendingChargeAmount: Double,
    @SerializedName("last_charged_date")
    val lastChargedDate: String,
    @SerializedName("last_charged_amount")
    val lastChargedAmount: Double,
    @SerializedName("semaphore")
    val semaphore: String
)