package com.example.numaboaterapia.register.psychologist.data.payment

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Results(

    @SerializedName("id")
    //@Expose
    var id: String? = null,
    @SerializedName("status")
    //@Expose
    var status: String? = null,
    @SerializedName("reason")
    //@Expose
    var reason: String? = null,
    @SerializedName("summarized")
    //@Expose
    var summarized: Summarized? = Summarized(),
    @SerializedName("payer_id")
    //@Expose
    var payerId: Int? = null,
    @SerializedName("back_url")
    //@Expose
    var backUrl: String? = null,
    @SerializedName("collector_id")
    //@Expose
    var collectorId: Int? = null,
    @SerializedName("application_id")
    //@Expose
    var applicationId: Int? = null,
    @SerializedName("external_reference")
    //@Expose
    var externalReference: String? = null,
    @SerializedName("date_created")
   // @Expose
    var dateCreated: String? = null,
    @SerializedName("last_modified")
    //@Expose
    var lastModified: String? = null,
    @SerializedName("preapproval_plan_id")
    //@Expose
    var preapprovalPlanId: String? = null,
    @SerializedName("auto_recurring")
    //@Expose
    var autoRecurring: AutoRecurring? = AutoRecurring(),
    @SerializedName("payment_method_id")
    //@Expose
    var paymentMethodId: String? = null,
    @SerializedName("payer_first_name")
    //@Expose
    var payerFirstName: String? = null,
    @SerializedName("payer_last_name")
    //@Expose
    var payerLastName: String? = null

)