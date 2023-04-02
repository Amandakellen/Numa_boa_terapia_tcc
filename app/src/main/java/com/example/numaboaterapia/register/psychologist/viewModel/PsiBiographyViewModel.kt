package com.example.numaboaterapia.register.psychologist.viewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.numaboaterapia.register.data.repository.UserFirebaseRegistrationRepository
import com.example.numaboaterapia.register.psychologist.data.Cep
import com.example.numaboaterapia.register.psychologist.data.repository.PsiBiographyRepository
import com.example.numaboaterapia.register.psychologist.model.ApiHelper
import com.example.numaboaterapia.register.psychologist.model.RetrofitBuilder
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class PsiBiographyViewModel : ViewModel() {
    private var repository = PsiBiographyRepository()
    private var firebaseRepository = UserFirebaseRegistrationRepository()

    private var _biography = MutableLiveData<String>()
    private var _cep = MutableLiveData<String>()
    private var _typeOfService = MutableLiveData<ArrayList<String>>()
    private var typeOfService: ArrayList<String> = arrayListOf()
    val loading = MutableLiveData<Boolean>()
    val error = MutableLiveData<Boolean>()
    val errorType = MutableLiveData<Int>()
    val cepData = MutableLiveData<Cep>()


    fun setBiography(text: String) {
        _biography.value = text
    }

    fun addTypeOfService(item: String) {
        typeOfService.add(item)
        _typeOfService.value = typeOfService
    }

    fun removeTypeOfService(item: String) {
        typeOfService.remove(item)
        _typeOfService.value = typeOfService
    }

    fun isTypeOfServiceNullOrEmpty(): Boolean = _typeOfService.value.isNullOrEmpty()

    fun isBiographyEmptyOrNull(): Boolean = _biography.value.isNullOrEmpty()

    fun isCepEmptyOrNull(): Boolean = _cep.value.isNullOrEmpty()

    fun setCep(cep: String) {
        _cep.value = cep
        repository.setCep(_cep.value.toString())
    }

    fun getLocation(){
        loading.value = true
        // Corroutine
        viewModelScope.launch {
            try {
                val currentCep = ApiHelper(RetrofitBuilder(_cep.value.toString()).apiService).getCep()
                cepData.value = currentCep
                if(currentCep.erro != null){
                    error.value = true
                    errorType.value = 1
                    loading.value = false
                }else {
                    cepData.value = currentCep
                    error.value = false
                    loading.value = false
                }
            }catch (e: Exception){
                error.value = true
                errorType.value = 0
                loading.value = false
            }
        }
    }

    fun getCepData() : Cep? = cepData.value
}