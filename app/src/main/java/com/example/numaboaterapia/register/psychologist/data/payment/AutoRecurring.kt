package com.example.numaboaterapia.register.psychologist.data.payment

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AutoRecurring (

    @SerializedName("frequency"                )
    //@Expose
    var frequency              : Int?     = null,
    @SerializedName("frequency_type"           )
    //@Expose
    var frequencyType          : String?  = null,
    @SerializedName("transaction_amount"       )
    //@Expose
    var transactionAmount      : Int?     = null,
    @SerializedName("currency_id"              )
    //@Expose
    var currencyId             : String?  = null,
    @SerializedName("start_date"               )
    //@Expose
    var startDate              : String?  = null,
    @SerializedName("has_billing_day"          )
    //@Expose
    var hasBillingDay          : Boolean? = null,
    @SerializedName("billing_day_proportional" )
    //@Expose
    var billingDayProportional : Boolean? = null

)