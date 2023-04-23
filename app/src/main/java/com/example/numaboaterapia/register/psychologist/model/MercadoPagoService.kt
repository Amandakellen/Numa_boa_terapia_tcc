package com.example.numaboaterapia.register.psychologist.model

import com.example.numaboaterapia.register.psychologist.data.Cep
import com.example.numaboaterapia.register.psychologist.data.payment.CheckPaymentData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query

interface MercadoPagoService {

    @Headers(
        value =
        ["Authorization:Bearer TEST-4233786830301324-040714-43e1a75eb8786cab806d62a9694d2329-476797907",
            "Content-type:application/json"]
    )
    @GET("search?")
     fun getPayment(
        @Query(
            "payer_email",
            encoded = false
        ) apikey: String
    ): Call<CheckPaymentData>
}