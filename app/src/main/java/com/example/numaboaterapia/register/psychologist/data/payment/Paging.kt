package com.example.numaboaterapia.register.psychologist.data.payment

import com.google.gson.annotations.SerializedName

data class Paging (
   // @SerializedName("total")
    var total: String,
    //@SerializedName("offset")
    var offset: String,
    //@SerializedName("limit")
    var limit: String)
//    override fun toString(): String {
//        return "ClassPojo [total = $total, offset = $offset, limit = $limit]"
//    }
//}