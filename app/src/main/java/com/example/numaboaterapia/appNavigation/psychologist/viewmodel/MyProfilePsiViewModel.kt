package com.example.numaboaterapia.appNavigation.psychologist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.numaboaterapia.appNavigation.psychologist.data.repository.GetFirebasePsiProfileRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async

class MyProfilePsiViewModel: ViewModel() {

    private val repository = GetFirebasePsiProfileRepository()
    private var registerData = MutableLiveData<ArrayList<String>>()
    private var  biographyData = MutableLiveData<ArrayList<String>>()
    private var specialtiesData = MutableLiveData<ArrayList<String>>()

    fun getBiographyData(): ArrayList<String>? = biographyData.value

    fun getRegisterData(): ArrayList<String>? = registerData.value

    fun getSpecialtiesData(): ArrayList<String>? = specialtiesData.value

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
            repository.getBiographyCollection().collect { it ->
                biographyData.value = it

            }

        }
        return result
    }

    fun getSpecialtiesCollection() : Deferred<Unit> {

        val result = viewModelScope.async {
            repository.getSpecialtiesCollection().collect { it ->
                specialtiesData.value = it

            }

        }
        return result
    }
}