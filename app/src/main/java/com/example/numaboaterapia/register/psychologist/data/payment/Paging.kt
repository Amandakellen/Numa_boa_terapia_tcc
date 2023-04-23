package com.example.numaboaterapia.register.psychologist.data.payment

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Paging (

    @SerializedName("offset" )
    //@Expose
    var offset : Int? = null,
    @SerializedName("limit"  )
    //@Expose
    var limit  : Int? = null,
    @SerializedName("total"  )
   // @Expose
    var total  : Int? = null

)