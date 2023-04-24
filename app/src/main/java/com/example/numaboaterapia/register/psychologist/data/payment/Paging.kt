package com.example.numaboaterapia.register.psychologist.data.payment


import com.google.gson.annotations.SerializedName

data class Paging(
    @SerializedName("offset")
    val offset: Int,
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("total")
    val total: Int
)