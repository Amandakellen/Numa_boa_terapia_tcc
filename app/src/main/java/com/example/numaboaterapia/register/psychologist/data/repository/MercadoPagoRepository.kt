package com.example.numaboaterapia.register.psychologist.data.repository

import com.example.numaboaterapia.register.psychologist.model.MercadoPagoService


class MercadoPagoRepository {
    private lateinit var email : String

    fun setEmail(emailValue : String){
        email = emailValue
    }
    suspend fun getPayment(apiService: MercadoPagoService) = apiService.getPayment(email)
}