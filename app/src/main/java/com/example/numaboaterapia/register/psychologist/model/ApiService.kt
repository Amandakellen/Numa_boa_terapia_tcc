package com.example.numaboaterapia.register.psychologist.model

import com.example.numaboaterapia.register.psychologist.data.Cep
import retrofit2.http.GET

/**
 *   Author:     Rodrigo Leutz
 *   Site:       https://uware.com.br
 *   Project:    CEP-MVVM
 *   Date:       02/11/20
 */

interface ApiService {

    @GET("customers/")
    suspend fun getCep(): Cep

}