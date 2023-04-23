package com.example.numaboaterapia.register.psychologist.data.payment

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CheckPaymentData (
    //@SerializedName("paging"  )
    //@Expose
    var paging  : Paging?            = Paging(),
    //@SerializedName("results" )
    //@Expose
    var results : ArrayList<Results> = arrayListOf()

)