package com.example.numaboaterapia.register.psychologist.data.payment

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Results(

    @SerializedName("id")
    val id: String,
    @SerializedName("version")
    val version: Int,
    @SerializedName("application_id")
    val applicationId: Long,
    @SerializedName("collector_id")
    val collectorId: Int,
    @SerializedName("preapproval_plan_id")
    val preapprovalPlanId: String,
    @SerializedName("reason")
    val reason: String,
    @SerializedName("external_reference")
    val externalReference: String,
    @SerializedName("back_url")
    val backUrl: String,
    @SerializedName("init_point")
    val initPoint: String,
    @SerializedName("auto_recurring")
    val autoRecurring: AutoRecurring,
    @SerializedName("first_invoice_offset")
    val firstInvoiceOffset: Int,
    @SerializedName("payer_id")
    val payerId: Int,
    @SerializedName("payer_first_name")
    val payerFirstName: String,
    @SerializedName("payer_last_name")
    val payerLastName: String,
    @SerializedName("card_id")
    val cardId: Long,
    @SerializedName("payment_method_id")
    val paymentMethodId: String,
    @SerializedName("next_payment_date")
    val nextPaymentDate: String,
    @SerializedName("date_created")
    val dateCreated: String,
    @SerializedName("last_modified")
    val lastModified: String,
    @SerializedName("summarized")
    val summarized: Summarized,
    @SerializedName("status")
    val status: String

)