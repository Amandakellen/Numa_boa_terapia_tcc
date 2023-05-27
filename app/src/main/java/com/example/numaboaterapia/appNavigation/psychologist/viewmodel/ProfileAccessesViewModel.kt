package com.example.numaboaterapia.appNavigation.psychologist.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.numaboaterapia.appNavigation.psychologist.data.repository.ProfileAccessesRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ProfileAccessesViewModel : ViewModel() {
    private val repository = ProfileAccessesRepository()

    private lateinit var userAccess: ArrayList<HashMap<String, String>>
    private lateinit var usersData: ArrayList<HashMap<String, String>>
    private lateinit var averageData: ArrayList<HashMap<String, String>>
    val filteredUsersData: MutableLiveData<ArrayList<HashMap<String, String>>> = MutableLiveData()
    val filteredAverageData: MutableLiveData<ArrayList<HashMap<String, String>>> = MutableLiveData()

    fun getUsersInformation(): ArrayList<HashMap<String, String>> = usersData

    fun getUsersAverageIncomeData(): ArrayList<HashMap<String, String>> = averageData


    suspend fun getAccessUsers() {
        val access = viewModelScope.async {
            repository.getAccessUsers()
        }

        access.await().collect { users ->
            userAccess = users
        }

    }

    suspend fun getUsersData() {
        val users = viewModelScope.async {
            repository.getUsersData()
        }

        users.await().collect { users ->
            usersData = users
        }
    }

    suspend fun getUsersAverageIncome() {
        val average = viewModelScope.async {
            repository.getUsersAverageIncome()
        }

        average.await().collect { it ->
            averageData = it
        }
    }

    fun getFilteredData(selectedDate: String) {
        viewModelScope.launch {
            try {
                val accessUsersData = repository.getAccessUsers().first()
                val usersData = repository.getUsersData().first()
                val averageData = repository.getUsersAverageIncome().first()

                // Filtrar os dados com a data selecionada
                val filteredUsersDataValue = usersData.filter { userData ->
                    val userDate = "${userData["day"]}/${userData["mounth"]}/${userData["year"]}"
                    userDate == selectedDate
                }
                filteredUsersData.postValue(
                    filteredUsersDataValue as ArrayList<HashMap<String, String>>)

                // Filtrar os dados médios com a data selecionada
                val filteredAverageDataValue = averageData.filter { average ->
                    val averageDate = "${average["day"]}/${average["mounth"]}/${average["year"]}"
                    averageDate == selectedDate
                }
                filteredAverageData.postValue(
                    filteredAverageDataValue as ArrayList<HashMap<String, String>>)
            } catch (exception: Exception) {
                // Lidar com possíveis erros
                exception.printStackTrace()
            }
        }
    }
}