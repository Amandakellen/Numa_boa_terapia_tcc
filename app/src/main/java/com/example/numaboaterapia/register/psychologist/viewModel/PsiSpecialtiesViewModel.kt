package com.example.numaboaterapia.register.psychologist.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.numaboaterapia.register.data.repository.FirebaseResponseRepository
import com.example.numaboaterapia.register.psychologist.data.PsiSpecialtiesEnum
import com.example.numaboaterapia.register.psychologist.data.repository.PsiSpecialtiesRepository
import com.example.numaboaterapia.register.psychologist.view.ActivityPsiSpecialties
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async

class PsiSpecialtiesViewModel : ViewModel() {
    private val specialtiesRepository = PsiSpecialtiesRepository()
    private val firebaseRepository = FirebaseResponseRepository()
    private var _search = MutableLiveData<String>()
    private var _itens = specialtiesRepository.setItensList()
    private var _itensSelected = ArrayList<String>()

    fun setSearch(value : String){
        _search.value = value
    }

    fun setItensSelected(itensSelected : ArrayList<String>){
        _itensSelected = itensSelected
    }

    fun getSearch() : String = _search.value.toString().lowercase()

    fun searchSize() = _search.value?.length

    fun searchIsNullOrEmpty() : Boolean = _search.value.isNullOrEmpty()

    fun getSpecialties(): ArrayList<PsiSpecialtiesEnum>
        = _itens

    fun saveValue(): Deferred<String> {

        val result = viewModelScope.async {
            firebaseRepository.saveData(
                "psi_specialties",
                hashMapOf("psi_specialties" to _itensSelected.joinToString(","))
            )
        }
        return result
    }
}