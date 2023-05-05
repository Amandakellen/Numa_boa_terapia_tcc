package com.example.numaboaterapia.appNavigation.pacient.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.numaboaterapia.appNavigation.pacient.data.repository.GetFirebaseProfileRepository
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async

class GetFirebaseProfileDataViewModel: ViewModel() {

    private var repository = GetFirebaseProfileRepository()
    private var data = MutableLiveData<ArrayList<String>>()



    fun getCollection() : Deferred<Unit> {

        val result = viewModelScope.async {
            repository.getCollection().collect { it ->
                data.value = it

            }

        }

        return result

    }


    fun getProfileData() : ArrayList<String>? = data.value
}