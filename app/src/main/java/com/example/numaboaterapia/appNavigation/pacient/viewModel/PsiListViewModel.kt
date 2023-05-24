package com.example.numaboaterapia.appNavigation.pacient.viewModel

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.numaboaterapia.appNavigation.pacient.data.repository.PsiListRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import java.io.ByteArrayOutputStream

class PsiListViewModel:ViewModel() {

    private val repositoryPsiData = PsiListRepository()
    private lateinit var psiUser : ArrayList<HashMap<String, String>>
    private lateinit var psiBiographyData : ArrayList<HashMap<String, String>>
    private lateinit var psiSpecialtiesData : ArrayList<HashMap<String, String>>
    private  var psiImage = mutableListOf<ByteArray>()
    private lateinit var imageDefaut : ByteArray

    fun setImageDefault(image : ByteArray){
        imageDefaut = image
    }
    fun getPsiUserData():ArrayList<HashMap<String, String>> = psiUser
    fun getPsiBiographyData():ArrayList<HashMap<String, String>> = psiBiographyData
    fun getPsiSpecialtiesData():ArrayList<HashMap<String, String>> = psiSpecialtiesData
    fun getImageData(): List<ByteArray> = psiImage

    suspend fun getPsiUser(){
        val psi = viewModelScope.async {
            repositoryPsiData.getPsiUsers()
        }

        psi.await().collect{ user->
            psiUser = user

        }

    }

    suspend fun getSpecialties(){
        val specialties = viewModelScope.async {
            repositoryPsiData.getPsiSpecialties()
        }

        specialties.await().collect{specialties->
            psiSpecialtiesData = specialties
        }
    }
    suspend fun getBiography(){
        val bio = viewModelScope.async {
            repositoryPsiData.getPsiBiography()
        }

        bio.await().collect{biography->
            psiBiographyData = biography
        }
    }

    suspend fun getImage(){
        repositoryPsiData.setImageDefault(imageDefaut)
        val result = viewModelScope.async {
            repositoryPsiData.getImageFilesFromStorage()
        }

        result.await().collect{list->
            psiImage = list.toMutableList()
        }

    }





}