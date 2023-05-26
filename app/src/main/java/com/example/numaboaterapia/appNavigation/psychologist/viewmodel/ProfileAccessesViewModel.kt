package com.example.numaboaterapia.appNavigation.psychologist.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.numaboaterapia.appNavigation.psychologist.data.repository.ProfileAccessesRepository
import kotlinx.coroutines.async

class ProfileAccessesViewModel: ViewModel() {
    private val repository=  ProfileAccessesRepository()

    private lateinit var usersData : ArrayList<HashMap<String, String>>
    private lateinit var averageData : ArrayList<HashMap<String, String>>

    fun getUsersInformation(): ArrayList<HashMap<String, String>> = usersData

    fun getUsersAverageIncomeData(): ArrayList<HashMap<String, String>> = averageData


    suspend fun getAccessUsers(){
        val access = viewModelScope.async {
            repository.getAccessUsers()
        }

    }

    suspend fun getUsersData(){
        val users = viewModelScope.async {
            repository.getUsersData()
        }

        users.await().collect{users->
            usersData = users
        }
    }

    suspend fun getUsersAverageIncome(){
        val average = viewModelScope.async {
            repository.getUsersAverageIncome()
        }

        average.await().collect{it->
            averageData = it
        }
    }
}