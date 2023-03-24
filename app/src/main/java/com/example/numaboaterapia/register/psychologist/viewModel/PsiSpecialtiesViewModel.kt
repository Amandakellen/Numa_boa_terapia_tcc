package com.example.numaboaterapia.register.psychologist.viewModel

import androidx.lifecycle.ViewModel
import com.example.numaboaterapia.register.psychologist.data.PsiSpecialtiesEnum
import com.example.numaboaterapia.register.psychologist.data.repository.PsiSpecialtiesRepository

class PsiSpecialtiesViewModel : ViewModel() {
    private val repository = PsiSpecialtiesRepository()

    fun getSpecialties(): ArrayList<PsiSpecialtiesEnum>
        = repository.setItensList()

}