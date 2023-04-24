package com.example.numaboaterapia.register.psychologist.data.payment

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AutoRecurring (

    @SerializedName("frequency")
    val frequency: Int,
    @SerializedName("frequency_type")
    val frequencyType: String,
    @SerializedName("start_date")
    val startDate: String,
    @SerializedName("end_date")
    val endDate: String,
    @SerializedName("currency_id")
    val currencyId: String,
    @SerializedName("transaction_amount")
    val transactionAmount: Double,
    @SerializedName("free_trial")
    val freeTrial: FreeTrial?

)