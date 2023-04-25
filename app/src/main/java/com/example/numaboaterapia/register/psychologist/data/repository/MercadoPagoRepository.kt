package com.example.numaboaterapia.register.psychologist.data.repository

import com.example.numaboaterapia.register.psychologist.data.payment.CheckPaymentData
import com.example.numaboaterapia.register.psychologist.model.MercadoPagoService
import retrofit2.awaitResponse


class MercadoPagoRepository {
    private lateinit var email: String

    fun setEmail(emailValue: String) {
        email = emailValue
    }

    suspend fun getSubscriptionByEmail(apiService: MercadoPagoService): CheckPaymentData? =
        apiService.getPayment(email)

}
