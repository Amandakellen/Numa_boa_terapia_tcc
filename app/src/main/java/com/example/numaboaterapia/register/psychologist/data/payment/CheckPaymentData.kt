package com.example.numaboaterapia.register.psychologist.data.payment

import com.google.gson.annotations.SerializedName

data class CheckPaymentData (
    @SerializedName("paging")
    var paging: Paging,
    @SerializedName("results")
    var results: Array<Results>? = null)
//    fun getPaging(): Paging? {
//        return paging
//    }

//    fun setPaging(paging: Paging?) {
//        this.paging = paging
//    }
//
//    fun getResults(): Array<Results>? {
//        return results
//    }
//
//    fun setResults(results: Array<Results>?) {
//        this.results = results
//    }
//
//    override fun toString(): String {
//        return "ClassPojo [paging = $paging, results = $results]"
//    }
