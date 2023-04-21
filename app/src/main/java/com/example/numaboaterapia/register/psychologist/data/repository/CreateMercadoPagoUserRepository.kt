package com.example.numaboaterapia.register.psychologist.data.repository

import com.example.numaboaterapia.register.psychologist.data.CreateUserData
import com.example.numaboaterapia.register.psychologist.model.MercadoPagoRetrofit

class CreateMercadoPagoUserRepository {
    private lateinit var body : CreateUserData

    companion object {
        const val TOKEN =
            "Bearer TEST-4233786830301324-040714-43e1a75eb8786cab806d62a9694d2329-476797907"
    }

    fun setBody(userBody : CreateUserData){
        body = userBody
    }


    suspend fun getUser()=
        MercadoPagoRetrofit(body).createUser.createUser(TOKEN,body)


}