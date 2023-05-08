package com.example.numaboaterapia.appNavigation.psychologist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.numaboaterapia.appNavigation.psychologist.data.repository.EditMyDataRepository
import com.example.numaboaterapia.appNavigation.psychologist.data.repository.GetFirebasePsiProfileRepository
import com.example.numaboaterapia.register.psychologist.data.PsiSpecialtiesEnum
import com.example.numaboaterapia.register.psychologist.data.repository.PsiSpecialtiesRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async

class MyProfilePsiViewModel : ViewModel() {

    private val specialtiesRepository = PsiSpecialtiesRepository()
    private val updateDataRepository = EditMyDataRepository()
    private val repository = GetFirebasePsiProfileRepository()
    private var registerData = MutableLiveData<ArrayList<String>>()
    private var biographyData = MutableLiveData<ArrayList<String>>()
    private var specialtiesData = MutableLiveData<ArrayList<String>>()
    private var _search = MutableLiveData<String>()
    private var _itens = specialtiesRepository.setItensList()
    private var _itensSelected = ArrayList<String>()

    fun setSearch(value: String) {
        _search.value = value
    }

    fun setItensSelected(itensSelected: ArrayList<String>) {
        _itensSelected = itensSelected
    }

    fun getSearch(): String = _search.value.toString().lowercase()

    fun searchSize() = _search.value?.length

    fun searchIsNullOrEmpty(): Boolean = _search.value.isNullOrEmpty()

    fun getSpecialties(): ArrayList<PsiSpecialtiesEnum> = _itens

    fun getBiographyData(): ArrayList<String>? = biographyData.value

    fun getRegisterData(): ArrayList<String>? = registerData.value

    fun getSpecialtiesData(): ArrayList<String>? = specialtiesData.value

    fun getRegisterCollection(): Deferred<Unit> {

        val result = viewModelScope.async {
            repository.getRegiterCollection().collect { it ->
                registerData.value = it

            }

        }

        return result

    }

    fun getBiographyCollection(): Deferred<Unit> {

        val result = viewModelScope.async {
            repository.getBiographyCollection().collect { it ->
                biographyData.value = it

            }

        }
        return result
    }

    fun getSpecialtiesCollection(): Deferred<Unit> {

        val result = viewModelScope.async {
            repository.getSpecialtiesCollection().collect { it ->
                specialtiesData.value = it

            }

        }
        return result
    }

    fun UpdateSpecialtiesCollection(): Deferred<Unit> {
        val result = viewModelScope.async {
            updateDataRepository.updatePsiSpecialtiesRegister(
                hashMapOf(
                    "psi_specialties" to _itensSelected.joinToString(
                        ","
                    )
                )
            ).collect({})
        }

        return result
    }

}