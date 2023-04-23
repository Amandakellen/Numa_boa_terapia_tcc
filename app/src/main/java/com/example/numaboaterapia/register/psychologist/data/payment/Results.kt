package com.example.numaboaterapia.register.psychologist.data.payment

data class Results (
    var reason: String? = null,
    var date_created: String? = null,
    var back_url: String? = null,
    var payer_last_name: String? = null,
    var summarized: String? = null,
    var application_id: String? = null,
    var payment_method_id: String? = null,
    var preapproval_plan_id: String? = null,
    var collector_id: String? = null,
    var external_reference: String? = null,
    var payer_id: String? = null,
    var id: String? = null,
    var last_modified: String? = null,
    var auto_recurring: AutoRecurring? = null,
    var status: String? = null,
    var payer_first_name: String? = null)
//    fun getAuto_recurring(): AutoRecurring? {
//        return auto_recurring
//    }
//
//    fun setAuto_recurring(auto_recurring: AutoRecurring?) {
//        this.auto_recurring = auto_recurring
//    }
//
//    override fun toString(): String {
//        return "ClassPojo [reason = $reason, " +
//                "date_created = $date_created, " +
//                "back_url = $back_url, " +
//                "payer_last_name = $payer_last_name, " +
//                "summarized = $summarized, " +
//                "application_id = $application_id, " +
//                "payment_method_id = $payment_method_id," +
//                " preapproval_plan_id = $preapproval_plan_id," +
//                " collector_id = $collector_id, " +
//                "external_reference = $external_reference, " +
//                "payer_id = $payer_id, " +
//                "id = $id, " +
//                "last_modified = $last_modified, " +
//                "auto_recurring = $auto_recurring, " +
//                "status = $status, " +
//                "payer_first_name = $payer_first_name]"
//    }
//}