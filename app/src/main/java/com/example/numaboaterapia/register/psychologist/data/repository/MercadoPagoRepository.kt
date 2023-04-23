package com.example.numaboaterapia.register.psychologist.data.repository

import com.example.numaboaterapia.register.psychologist.model.MercadoPagoService
import retrofit2.awaitResponse


class MercadoPagoRepository {
    private lateinit var email : String

    fun setEmail(emailValue : String){
        email = emailValue
    }
     fun getPayment(apiService: MercadoPagoService) = apiService.getPayment(email)
}