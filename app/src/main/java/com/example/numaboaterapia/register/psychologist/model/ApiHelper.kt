package com.example.numaboaterapia.register.psychologist.model

/**
 *   Author:     Rodrigo Leutz
 *   Site:       https://uware.com.br
 *   Project:    CEP-MVVM
 *   Date:       02/11/20
 */

class ApiHelper(private val apiService: ApiService) {
    suspend fun getCep() = apiService.getCep()
}