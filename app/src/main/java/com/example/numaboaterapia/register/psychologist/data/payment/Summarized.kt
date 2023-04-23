package com.example.numaboaterapia.register.psychologist.data.payment

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Summarized (

    @SerializedName("semaphore"           )
    //@Expose
    var semaphore         : String? = null,
    @SerializedName("charged_quantity"    )
    //@Expose
    var chargedQuantity   : Int?    = null,
    @SerializedName("charged_amount"      )
    //@Expose
    var chargedAmount     : Int?    = null,
    @SerializedName("last_charged_date"   )
    //@Expose
    var lastChargedDate   : String? = null,
    @SerializedName("last_charged_amount" )
    //@Expose
    var lastChargedAmount : Int?    = null

)