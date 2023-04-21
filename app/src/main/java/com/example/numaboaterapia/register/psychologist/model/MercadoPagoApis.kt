package com.example.numaboaterapia.register.psychologist.model

import com.example.numaboaterapia.register.psychologist.data.CreateUserData
import com.example.numaboaterapia.register.psychologist.data.MercadoPagoClientData
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface MercadoPagoApis {

    @POST("customers/")
     fun createUser(
        @Header("Authorization") token: String, @Body
        body: CreateUserData
    ): Call<MercadoPagoClientData>

}