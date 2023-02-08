package com.example.numaboaterapia.Login.ViewModel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.numaboaterapia.Login.data.repository.ForgetPasswordRepository
import kotlinx.coroutines.launch

class ForgetPasswordViewModel(val application: Application) : ViewModel() {
    private val repository: ForgetPasswordRepository = ForgetPasswordRepository()

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    var result: Boolean? = null

    fun emailValue(email: String) {
        _email.value = email
    }

    fun verifyPassword() {
        viewModelScope.launch {
            result = repository.sendEmail(application, _email.value.toString())
        }

    }
}