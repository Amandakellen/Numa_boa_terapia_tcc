package com.example.numaboaterapia.register.psychologist.viewModel

import android.util.Log
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
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Response
import retrofit2.awaitResponse

class CreateMercadoPagoUserViewModel : ViewModel() {

    val firebaseRepository = FirebaseResponseRepository()
    val repository =  MercadoPagoRepository()

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

    fun verifyPayment(){
        if(_subscription.value != null){
            val result = _subscription.value!!.results
            result.forEach {
                it.dateCreated
            }
        }

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