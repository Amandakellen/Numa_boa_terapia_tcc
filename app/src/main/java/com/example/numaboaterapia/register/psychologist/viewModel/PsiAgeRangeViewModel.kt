package com.example.numaboaterapia.register.psychologist.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.numaboaterapia.register.data.RegisterResponseEnum
import com.example.numaboaterapia.register.data.repository.FirebaseResponseRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async

class PsiAgeRangeViewModel: ViewModel() {
    var itens: ArrayList<RegisterResponseEnum> = arrayListOf()
    private val repository =  FirebaseResponseRepository()

    fun setDataItens(): ArrayList<RegisterResponseEnum> {
        itens.add(RegisterResponseEnum.CHILDREN)
        itens.add(RegisterResponseEnum.TEENAGERS)
        itens.add(RegisterResponseEnum.ADULTS)
        itens.add(RegisterResponseEnum.ELDERLY)
        itens.add(RegisterResponseEnum.COUPLES)

        return itens

    }

    fun saveValue(financialSituation : String): Deferred<String> {
        val result = viewModelScope.async {
            repository.saveData(
                "psi_service_age_range",
                hashMapOf("psi_service_age_range" to financialSituation)
            )
        }
        return result
    }
}