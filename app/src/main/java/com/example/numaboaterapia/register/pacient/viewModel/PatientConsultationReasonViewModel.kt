package com.example.numaboaterapia.register.pacient.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.numaboaterapia.register.pacient.data.ConsultationReasonEnum
import com.example.numaboaterapia.register.pacient.data.repository.PatientConsultationReasonRepository
import com.example.numaboaterapia.register.pacient.data.repository.PatientRegistrationRepository
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async

class PatientConsultationReasonViewModel: ViewModel() {
    var itens: ArrayList<ConsultationReasonEnum> = arrayListOf<ConsultationReasonEnum>()
    private val repository: PatientConsultationReasonRepository =
        PatientConsultationReasonRepository()

    private var _patientReason = MutableLiveData<ConsultationReasonEnum>()
    var patientReason: LiveData<ConsultationReasonEnum> = _patientReason

    fun getDataItens(): ArrayList<ConsultationReasonEnum> {

        itens.add(ConsultationReasonEnum.ANXIETY)
        itens.add(ConsultationReasonEnum.PERSONAL_GROWTH)
        itens.add(ConsultationReasonEnum.DEPRESSION)
        itens.add(ConsultationReasonEnum.GRIEF)
        itens.add(ConsultationReasonEnum.COUPLE)
        itens.add(ConsultationReasonEnum.EATING_DISORDER)
        itens.add(ConsultationReasonEnum.SEXUALITY)
        itens.add(ConsultationReasonEnum.OTHER)

        return itens

    }

    fun setPatientReason(selectValue: ConsultationReasonEnum) = _patientReason.value


    //  fun saveValue(): Deferred<String> {
//        val result = viewModelScope.async {
//
//        }
//        return result
    //}

}