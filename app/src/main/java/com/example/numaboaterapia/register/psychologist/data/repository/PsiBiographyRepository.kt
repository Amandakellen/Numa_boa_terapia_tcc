package com.example.numaboaterapia.register.psychologist.data.repository

import com.example.numaboaterapia.register.psychologist.model.ApiService


class PsiBiographyRepository {
    private var cep : String = " "
    private var location : String = ""

    fun setCep(cepValue : String) {
        cep = cepValue
    }


    suspend fun getCep(apiService: ApiService) = apiService.getCep()
}