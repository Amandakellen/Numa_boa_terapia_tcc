package com.example.numaboaterapia.register.psychologist.data.payment

data class AutoRecurring (
    var transaction_amount: String? = null,
    var has_billing_day: String? = null,
    var billing_day_proportional: String? = null,
    var frequency_type: String? = null,
    var currency_id: String? = null,
    var frequency: String? = null,
    var start_date: String? = null)
//    override fun toString(): String {
//        return "ClassPojo [transaction_amount = $transaction_amount," +
//                " has_billing_day = $has_billing_day," +
//                " billing_day_proportional = $billing_day_proportional, " +
//                "frequency_type = $frequency_type, " +
//                "currency_id = $currency_id, frequency = $frequency, " +
//                "start_date = $start_date]"
//    }
//}