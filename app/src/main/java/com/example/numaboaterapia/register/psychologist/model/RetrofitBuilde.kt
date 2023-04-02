package com.example.numaboaterapia.register.psychologist.model

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 *   Author:     Rodrigo Leutz
 *   Site:       https://uware.com.br
 *   Project:    CEP-MVVM
 *   Date:       02/11/20
 */

class RetrofitBuilder(cep: String) {

    private val cep = cep

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://viacep.com.br/ws/${cep}/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiService: ApiService = getRetrofit().create(ApiService::class.java)
}