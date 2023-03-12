package com.example.numaboaterapia.register.pacient.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.numaboaterapia.register.data.RegisterResponseEnum
import com.example.numaboaterapia.register.data.repository.FirebaseResponseRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async

class PatientConsultationReasonViewModel: ViewModel() {
    var itens: ArrayList<RegisterResponseEnum> = arrayListOf()
    private val repository: FirebaseResponseRepository =
        FirebaseResponseRepository()

    fun getDataItens(): ArrayList<RegisterResponseEnum> {

        itens.add(RegisterResponseEnum.ANXIETY)
        itens.add(RegisterResponseEnum.PERSONAL_GROWTH)
        itens.add(RegisterResponseEnum.DEPRESSION)
        itens.add(RegisterResponseEnum.GRIEF)
        itens.add(RegisterResponseEnum.COUPLE)
        itens.add(RegisterResponseEnum.EATING_DISORDER)
        itens.add(RegisterResponseEnum.SEXUALITY)
        itens.add(RegisterResponseEnum.OTHER)

        return itens

    }

      fun saveValue(reason : String): Deferred<String> {
        val result = viewModelScope.async {
              repository.saveData(
                  "patient_reason",
                  hashMapOf("pr_reason" to reason)
              )
        }
        return result
    }

}