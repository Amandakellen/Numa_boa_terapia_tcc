package com.example.numaboaterapia.appNavigation.pacient.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.numaboaterapia.appNavigation.pacient.data.repository.PsiProfileInformationRepository
import com.example.numaboaterapia.register.data.repository.FirebaseResponseRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.collect
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class PsiProfileInformationViewModel: ViewModel() {
    private var repository = PsiProfileInformationRepository()
    private var firebaseResponseRepository = FirebaseResponseRepository()
    private var _dateTime = MutableLiveData<String>()
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

    private fun hashMapData(): HashMap<String, String> {
        setDate()
        return hashMapOf(
            "psi_uid" to uId,
            "accesses_date" to _dateTime.value.toString()

        )

    }

    private fun setDate() {
        val currentDateTime = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
        _dateTime.value = currentDateTime.format(formatter)
    }
     fun savePacientData():Deferred<String>{
        setDate()
        val result = viewModelScope.async {
            firebaseResponseRepository.saveData(
                "patient_accesses",
                hashMapData()
            )
        }
        return result
    }
}