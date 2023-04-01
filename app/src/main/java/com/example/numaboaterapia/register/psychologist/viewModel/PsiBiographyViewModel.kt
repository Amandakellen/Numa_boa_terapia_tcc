package com.example.numaboaterapia.register.psychologist.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.numaboaterapia.register.data.repository.UserFirebaseRegistrationRepository
import com.example.numaboaterapia.register.psychologist.data.repository.PsiBiographyRepository

class PsiBiographyViewModel: ViewModel() {
    private var repository =  PsiBiographyRepository()
    private var firebaseRepository = UserFirebaseRegistrationRepository()

    private lateinit var _biography : MutableLiveData<String>
    private lateinit var _cep : MutableLiveData<String>

    fun setBiography(text : String){
        _biography.value = text
    }

    fun setCep(cep: String){
        _cep.value = cep
    }

}