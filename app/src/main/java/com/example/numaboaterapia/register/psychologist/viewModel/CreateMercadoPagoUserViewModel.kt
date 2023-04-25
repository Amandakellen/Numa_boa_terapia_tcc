package com.example.numaboaterapia.register.psychologist.viewModel

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.numaboaterapia.register.data.repository.FirebaseResponseRepository
import com.example.numaboaterapia.register.psychologist.data.payment.CheckPaymentData
import com.example.numaboaterapia.register.psychologist.data.repository.MercadoPagoRepository
import com.example.numaboaterapia.register.psychologist.model.RetrofitMercadoPago
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import java.text.SimpleDateFormat
import java.time.temporal.ChronoUnit
import java.util.*

class CreateMercadoPagoUserViewModel : ViewModel() {

    val firebaseRepository = FirebaseResponseRepository()
    val repository = MercadoPagoRepository()

    private val _email = MutableLiveData<String>()
    private val _name = MutableLiveData<String>()
    private val _cpf = MutableLiveData<String>()

    private lateinit var firstName: String
    private lateinit var lastName: String

    private val _subscription = MutableLiveData<CheckPaymentData?>()
    val subscription: LiveData<CheckPaymentData?>
        get() = _subscription

    fun nameValue(name: String) {
        _name.value = name
    }

    fun emailValue(email: String) {
        _email.value = email
    }

    fun getCPF(cpf: String) {
        _cpf.value = cpf
    }

    private fun configName() {
        val nameList = _name.value?.split(" ")
        val size = nameList?.size?.minus(1)
        firstName = nameList?.get(0) ?: " "
        lastName = size?.let { nameList?.get(it) } ?: " "

    }

    fun checkIfIsNullOrEmpty(): Boolean =
        (_name.value?.isNullOrEmpty() == null ||
                _name.value?.isNullOrEmpty() == true ||
                _cpf.value?.isNullOrEmpty() == null ||
                _cpf.value?.isNullOrEmpty() == true ||
                _email.value?.isNullOrEmpty() == null ||
                _email.value?.isNullOrEmpty() == true)

    private fun hashMapData(): HashMap<String, String> {
        return hashMapOf(
            "email_cobranca" to _email.value.toString(),
            "cpf" to _cpf.value.toString(),
            "first_name" to firstName,
            "last_name" to lastName

        )

    }

    fun createUser(): Deferred<String> {
        configName()
        val result = viewModelScope.async {
            firebaseRepository.saveData(
                "psi_mercado_pago",
                hashMapData()
            )
        }
        return result
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun checkPaymentDate(paymentDate: ZonedDateTime?, type: String): String {
        val currentDateTime = ZonedDateTime.now()
        var hasPassed: Boolean = true
        when (type) {
            "m" -> {
                hasPassed = ChronoUnit.MONTHS.between(paymentDate, currentDateTime) >= 1

            }

            "a" -> {
                hasPassed = ChronoUnit.YEARS.between(paymentDate, currentDateTime) >= 1
            }

            else -> {
                hasPassed = ChronoUnit.MONTHS.between(paymentDate, currentDateTime) >= 6
            }
        }

        if (hasPassed) {
            return "canceled"
        } else {
            return "authorized"
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun verifyPayment(): String? {
        var dateTimeString: String? = null
        val formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME
        var paymentDate: ZonedDateTime? = null
        var status: String? = null


        if (_subscription.value != null) {
            if (_subscription.value!!.results != null) {
                val result = _subscription.value!!.results
                result.forEach {
                    dateTimeString = it.dateCreated
                    paymentDate = ZonedDateTime.parse(dateTimeString, formatter)

                    if (it.status == "authorized") {
                        when (it.externalReference) {
                            "NUMABOAMENSAL" -> {
                                status = checkPaymentDate(paymentDate, "m")
                            }

                            "NUMABOAANUAL" -> {
                                status = checkPaymentDate(paymentDate, "a")
                            }

                            else -> {
                                status = checkPaymentDate(paymentDate, "s")
                            }
                        }

                    }

                }
            } else {
                return "erro"
            }

        }

        return status
    }

    fun getPayment(): Deferred<Unit> {
        repository.setEmail(_email.value.toString())
        val result = viewModelScope.async {
            val response = repository.getSubscriptionByEmail(RetrofitMercadoPago().apiService)
            _subscription.value = response
        }

        return result
    }
}