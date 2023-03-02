package com.example.numaboaterapia.register.pacient.viewModel

import androidx.lifecycle.ViewModel
import com.example.numaboaterapia.register.pacient.data.ConsultationReasonEnum

class PatientConsultationReasonViewModel: ViewModel() {
    lateinit var itens : ArrayList<ConsultationReasonEnum>

    fun getDataItens(): ArrayList<ConsultationReasonEnum>{
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
}