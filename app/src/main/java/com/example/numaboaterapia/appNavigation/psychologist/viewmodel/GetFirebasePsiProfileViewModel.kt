package com.example.numaboaterapia.appNavigation.psychologist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.numaboaterapia.Login.data.repository.LoginRepository
import com.example.numaboaterapia.appNavigation.psychologist.data.repository.GetFirebasePsiProfileRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async

class GetFirebasePsiProfileViewModel: ViewModel() {

    private var repository = GetFirebasePsiProfileRepository()
    private var loginRepository = LoginRepository()

    private var registerData = MutableLiveData<ArrayList<String>>()
    private var biographyData = MutableLiveData<ArrayList<String>>()

    fun getRegisterCollection() : Deferred<Unit> {

        val result = viewModelScope.async {
            repository.getRegiterCollection().collect { it ->
                registerData.value = it

            }

        }

        return result

    }

    fun getBiographyCollection() : Deferred<Unit> {

        val result = viewModelScope.async {
            repository.getBiographyrCollection().collect { it ->
                biographyData.value = it

            }

        }

        return result

    }

    fun getBiographyData(): ArrayList<String>? = biographyData.value

    fun getRegisterData(): ArrayList<String>? = registerData.value


}