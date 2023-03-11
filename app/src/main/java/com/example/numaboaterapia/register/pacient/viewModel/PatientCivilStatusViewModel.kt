package com.example.numaboaterapia.register.pacient.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.numaboaterapia.register.data.RegisterResponseEnum
import com.example.numaboaterapia.register.data.repository.FirebaseResponseRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async

class PatientCivilStatusViewModel : ViewModel() {
    var itens: ArrayList<RegisterResponseEnum> = arrayListOf()
    private val repository =  FirebaseResponseRepository()

        fun setDataItens(): ArrayList<RegisterResponseEnum> {
            itens.add(RegisterResponseEnum.SINGLE)
            itens.add(RegisterResponseEnum.RELATIONSHIP)
            itens.add(RegisterResponseEnum.DIVORCED)
            itens.add(RegisterResponseEnum.WIDOWER)
            itens.add(RegisterResponseEnum.DONT_INFORM)


            return itens

        }
    fun saveValue(civilStatus : String): Deferred<String> {
        val result = viewModelScope.async {
            repository.saveData(
                "patiant_civil_status",
                hashMapOf("pc_civil_status" to civilStatus)
            )
        }
        return result
    }
}