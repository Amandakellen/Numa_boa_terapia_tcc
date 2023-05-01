package com.example.numaboaterapia.appNavigation.pacient.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.numaboaterapia.appNavigation.pacient.data.DiaryItensEnum
import com.example.numaboaterapia.appNavigation.pacient.data.repository.PatientDiaryRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class EmocionalDiaryViewModel: ViewModel() {

    private var itens: ArrayList<DiaryItensEnum> = arrayListOf()
    private val firebaseDataRepository = PatientDiaryRepository("patient_diary")
    private lateinit var data: HashMap<Int, Any>

    fun setDataItens(): ArrayList<DiaryItensEnum> {

        itens.add(DiaryItensEnum.AMOR)
        itens.add(DiaryItensEnum.FELICIDADE)
        itens.add(DiaryItensEnum.LEVEZA)
        itens.add(DiaryItensEnum.TRANQUILIDADE)
        itens.add(DiaryItensEnum.ANSIEDADE)
        itens.add(DiaryItensEnum.TRISTEZA)
        itens.add(DiaryItensEnum.CANSACO)
        itens.add(DiaryItensEnum.IRRITACAO)
        itens.add(DiaryItensEnum.FRUSTRACAO)
        itens.add(DiaryItensEnum.ESTRESSE)
        itens.add(DiaryItensEnum.MEDO)
        itens.add(DiaryItensEnum.VERGONHA)

        return itens
    }

    fun getCollection() : Deferred<Unit> {

       val result = viewModelScope.async {
            firebaseDataRepository.getCollection().collect { dataHashMap ->
                data = dataHashMap

            }

        }

        return result

    }


    fun isPatiantDiaryEmpty(): Boolean = data.isEmpty()


}