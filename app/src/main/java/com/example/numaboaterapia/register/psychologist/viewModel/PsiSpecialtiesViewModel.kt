package com.example.numaboaterapia.register.psychologist.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.numaboaterapia.register.psychologist.data.PsiSpecialtiesEnum
import com.example.numaboaterapia.register.psychologist.data.repository.PsiSpecialtiesRepository

class PsiSpecialtiesViewModel : ViewModel() {
    private val repository = PsiSpecialtiesRepository()
    private var _search = MutableLiveData<String>()
    private var _itens = repository.setItensList()
    private var _itensSearch = MutableLiveData<PsiSpecialtiesEnum>()

    fun setSearch(value : String){
        _search.value = value
    }

    fun getSearch() : String = _search.value.toString().lowercase()

    fun searchSize() = _search.value?.length

    fun searchIsNullOrEmpty() : Boolean = _search.value.isNullOrEmpty()

    fun getSpecialties(): ArrayList<PsiSpecialtiesEnum>
        = _itens

}