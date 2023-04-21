package com.example.numaboaterapia.register.psychologist.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.numaboaterapia.register.data.repository.FirebaseResponseRepository
import com.example.numaboaterapia.register.psychologist.data.CreateUserData
import com.example.numaboaterapia.register.psychologist.data.repository.CreateMercadoPagoUserRepository
import kotlinx.coroutines.launch
import java.lang.Exception

class CreateMercadoPagoUserViewModel : ViewModel() {

    val firebaseRepository = FirebaseResponseRepository()
    val createUserRepository = CreateMercadoPagoUserRepository()

    private val _email = MutableLiveData<String>()
    private val _name = MutableLiveData<String>()
    private val _cpf = MutableLiveData<String>()

    private lateinit var firstName: String
    private lateinit var lastName: String

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
                _cpf.value?.isNullOrEmpty() == true||
                _email.value?.isNullOrEmpty() == null ||
                _email.value?.isNullOrEmpty() == true)

    fun createUser() {
        configName()
        val body =
            CreateUserData(firstName, lastName, _cpf.value.toString(), _email.value.toString())

        viewModelScope.launch{
            try{
                createUserRepository.setBody(body)
                val result = createUserRepository.getUser()

            }catch (e : Exception){
                Log.i("erro", e.message.toString())
            }
        }
    }
}