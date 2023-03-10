package com.example.numaboaterapia.register.pacient.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.numaboaterapia.register.data.RegisterResponseEnum
import com.example.numaboaterapia.register.data.repository.FirebaseResponseRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async

class PatientFinancialSituationViewmodel: ViewModel() {

    var itens: ArrayList<RegisterResponseEnum> = arrayListOf()
    private val repository =  FirebaseResponseRepository()

    fun setDataItens(): ArrayList<RegisterResponseEnum> {
        itens.add(RegisterResponseEnum.EXCELLENT)
        itens.add(RegisterResponseEnum.GOOD)
        itens.add(RegisterResponseEnum.STABLE)
        itens.add(RegisterResponseEnum.BAD)
        itens.add(RegisterResponseEnum.DONT_KNOW)


        return itens

    }
    fun saveValue(financialSituation : String): Deferred<String> {
        val result = viewModelScope.async {
            repository.saveData(
                "patiant_financial_situation",
                hashMapOf("pfc_financial_situation" to financialSituation)
            )
        }
        return result
    }
}