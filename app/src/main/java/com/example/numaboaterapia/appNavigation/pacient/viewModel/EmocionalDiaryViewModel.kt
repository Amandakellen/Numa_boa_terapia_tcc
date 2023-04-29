package com.example.numaboaterapia.appNavigation.pacient.viewModel

import androidx.lifecycle.ViewModel
import com.example.numaboaterapia.appNavigation.pacient.data.DiaryItensEnum

class EmocionalDiaryViewModel: ViewModel() {

    private var itens: ArrayList<DiaryItensEnum> = arrayListOf()

    fun setDataItens():ArrayList<DiaryItensEnum>{

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
}