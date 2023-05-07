package com.example.numaboaterapia.appNavigation.psychologist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.numaboaterapia.appNavigation.psychologist.data.repository.GetFirebasePsiProfileRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async

class GetFirebasePsiMyDataViewModel: ViewModel() {

    private var repository = GetFirebasePsiProfileRepository()

    private var registerData = MutableLiveData<ArrayList<String>>()

    fun getRegisterCollection() : Deferred<Unit> {

        val result = viewModelScope.async {
            repository.getRegiterCollection().collect { it ->
                registerData.value = it

            }

        }

        return result

    }

    fun getRegisterData(): ArrayList<String>? = registerData.value


}