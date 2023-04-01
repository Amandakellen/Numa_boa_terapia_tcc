package com.example.numaboaterapia.register.psychologist.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.numaboaterapia.register.data.repository.UserFirebaseRegistrationRepository
import com.example.numaboaterapia.register.psychologist.data.repository.PsiBiographyRepository

class PsiBiographyViewModel : ViewModel() {
    private var repository = PsiBiographyRepository()
    private var firebaseRepository = UserFirebaseRegistrationRepository()

    private var _biography = MutableLiveData<String>()
    private var _cep = MutableLiveData<String>()
    private var _typeOfService = MutableLiveData<ArrayList<String>>()
    private var typeOfService: ArrayList<String> = arrayListOf()


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


    fun setCep(cep: String) {
        _cep.value = cep
    }

}