package com.example.numaboaterapia.appNavigation.pacient.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.numaboaterapia.appNavigation.pacient.data.repository.PsiProfileInformationRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collect

class PsiProfileInformationViewModel: ViewModel() {
    private var repository = PsiProfileInformationRepository()
    private lateinit var psiUser : ArrayList< String>
    private lateinit var psiBiographyData : ArrayList<String>
    private lateinit var psiSpecialtiesData : ArrayList<String>
    private lateinit var psiImage : ByteArray
    private lateinit var uId : String

    fun getuId(id : String){
        uId = id
    }

    fun getImageData(): ByteArray = psiImage

    fun getPsiUser(): ArrayList<String> = psiUser

    fun getPsiBiography(): ArrayList<String> = psiBiographyData

    fun getPsiSpecialties(): ArrayList<String> = psiSpecialtiesData

    suspend fun getRegisterCollection(){

        val result = viewModelScope.async {
            repository.getRegiterCollection(uId)

        }

        result.await().collect{
            psiUser = it
        }

    }

    suspend fun getBiographyCollection() {

        val result = viewModelScope.async {
            repository.getBiographyCollection(uId)

        }

        result.await().collect{
            psiBiographyData = it
        }
    }

    suspend fun getSpecialtiesCollection(){

        val result = viewModelScope.async {
            repository.getSpecialtiesCollection(uId)

        }

        result.await().collect{
            psiSpecialtiesData = it
        }

    }

    suspend fun getImage(){
        val result  = viewModelScope.async{
            repository.getFirebaseFileImage(uId)
        }

        result.await().collect{
            psiImage = it
        }
    }
}