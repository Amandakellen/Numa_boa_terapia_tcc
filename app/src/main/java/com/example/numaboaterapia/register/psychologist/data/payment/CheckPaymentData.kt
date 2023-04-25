package com.example.numaboaterapia.register.psychologist.data.payment

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CheckPaymentData (
    @SerializedName("paging")
    var paging: Paging,
    @SerializedName("results")
    var results: List<Results>
)