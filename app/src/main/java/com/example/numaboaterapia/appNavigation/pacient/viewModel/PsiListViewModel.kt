package com.example.numaboaterapia.appNavigation.pacient.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.numaboaterapia.appNavigation.pacient.data.repository.PsiListRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow

class PsiListViewModel:ViewModel() {

    private val repositoryPsiData = PsiListRepository()
    private lateinit var psiUser : Flow<ArrayList<HashMap<String, String>>>
    private lateinit var psiBiography : Flow<ArrayList<HashMap<String, String>>>

    suspend fun getPsiList(){
        val psi = viewModelScope.async {
            repositoryPsiData.getPsiUsers()
        }

        val bio = viewModelScope.async {
            repositoryPsiData.getPsiBiography()
        }

        psiUser = psi.await()
        psiBiography = bio.await()




    }

}