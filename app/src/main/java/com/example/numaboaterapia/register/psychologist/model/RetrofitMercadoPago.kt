package com.example.numaboaterapia.register.psychologist.model

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitMercadoPago() {

    val gson = GsonBuilder().create()
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.mercadopago.com")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(
                OkHttpClient.Builder()
                    .addInterceptor { chain ->
                        val request = chain.request().newBuilder()
                            .addHeader(
                                "Authorization",
                                "Bearer TEST-4233786830301324-040714-43e1a75eb8786cab806d62a9694d2329-476797907"
                            )
                            .build()
                        chain.proceed(request)
                    }.build()
            )
            .build()
    }


    val apiService: MercadoPagoService = getRetrofit().create(MercadoPagoService::class.java)
}