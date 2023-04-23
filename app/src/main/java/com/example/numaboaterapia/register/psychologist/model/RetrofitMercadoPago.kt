package com.example.numaboaterapia.register.psychologist.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitMercadoPago() {


    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.mercadopago.com/preapproval/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService : MercadoPagoService = getRetrofit().create(MercadoPagoService::class.java)
}