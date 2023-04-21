package com.example.numaboaterapia.register.psychologist.model

import com.example.numaboaterapia.register.psychologist.data.CreateUserData
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MercadoPagoRetrofit(body : CreateUserData) {

    companion object {
        const val BASE_URL = "https://api.mercadopago.com/v1/"
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val createUser : MercadoPagoApis = getRetrofit().create(MercadoPagoApis::class.java)
}