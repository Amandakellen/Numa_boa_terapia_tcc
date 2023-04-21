package com.example.numaboaterapia.register.psychologist.data

import com.google.gson.annotations.SerializedName

data class MercadoPagoClientData(
    @SerializedName("id")
    val id: String?,
    @SerializedName("email")
    val email: String?,
    @SerializedName("first_name")
    val first_name: String?,
    @SerializedName("last_name")
    val last_name: String?,
    @SerializedName("phone")
    val phone: String?,
    @SerializedName("identification")
    val identification: String?,
    @SerializedName("address")
    val address: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("date_created")
    val date_created: String?,
    @SerializedName("metadata")
    val metadata: String?,
    @SerializedName("default_address")
    val default_address: String?,
    @SerializedName("cards")
    val cards: String?,
    @SerializedName("addresses")
    val addresses: String?,
    @SerializedName("live_mode")
    val live_mode: String?

)
