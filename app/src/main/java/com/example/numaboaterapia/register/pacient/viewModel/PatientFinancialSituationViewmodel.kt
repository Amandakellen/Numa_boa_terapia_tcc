package com.example.numaboaterapia.register.pacient.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.numaboaterapia.register.pacient.data.PatientResponseEnum
import com.example.numaboaterapia.register.pacient.data.repository.PatientResponseRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async

class PatientFinancialSituationViewmodel: ViewModel() {

    var itens: ArrayList<PatientResponseEnum> = arrayListOf()
    private val repository =  PatientResponseRepository()

    fun setDataItens(): ArrayList<PatientResponseEnum> {
        itens.add(PatientResponseEnum.EXCELLENT)
        itens.add(PatientResponseEnum.GOOD)
        itens.add(PatientResponseEnum.STABLE)
        itens.add(PatientResponseEnum.BAD)
        itens.add(PatientResponseEnum.DONT_KNOW)


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