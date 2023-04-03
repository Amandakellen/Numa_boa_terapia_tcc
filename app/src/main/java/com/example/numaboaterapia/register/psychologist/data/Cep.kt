package com.example.numaboaterapia.register.psychologist.data

import com.google.gson.annotations.SerializedName

/**
 *   Author:     Rodrigo Leutz
 *   Site:       https://uware.com.br
 *   Project:    CEP-MVVM
 *   Date:       02/11/20
 */

data class Cep(
    @SerializedName("cep")
    val cep: String?,
    @SerializedName("logradouro")
    val logradouro: String?,
    @SerializedName("bairro")
    val bairro: String?,
    @SerializedName("localidade")
    val localidade: String?,
    @SerializedName("uf")
    val uf: String?,
    @SerializedName("ddd")
    val ddd: String?,
    @SerializedName("erro")
    val erro: String?
)