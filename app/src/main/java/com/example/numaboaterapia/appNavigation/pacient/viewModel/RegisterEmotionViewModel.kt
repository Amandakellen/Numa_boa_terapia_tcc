package com.example.numaboaterapia.appNavigation.pacient.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.numaboaterapia.appNavigation.pacient.data.repository.PatientDiaryRepository
import com.example.numaboaterapia.register.data.repository.FirebaseResponseRepository
import com.google.firebase.Timestamp
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class RegisterEmotionViewModel : ViewModel() {
    private val repository = FirebaseResponseRepository()
    private var _diaryText = MutableLiveData<String>()
    private var _feeling = MutableLiveData<String>()
    private var _dateTime = MutableLiveData<String>()


    fun setFeeling(feeling: String) {
        _feeling.value = feeling
    }

    fun setDiaryText(text: String) {
        _diaryText.value = text
    }

    private fun hashMapData(): HashMap<String, Any> {
        setDate()
        val diaryDate =
            SimpleDateFormat("dd/MM/yyyy - HH:mm", Locale.getDefault()).parse(_dateTime.value)
        val timestamp = Timestamp(diaryDate)
        return hashMapOf(
            "diary_feeling" to _feeling.value.toString(),
            "diary_text" to _diaryText.value.toString(),
            "diary_date" to timestamp

        )

    }

    private fun setDate() {
        val currentDateTime = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm")
        _dateTime.value = currentDateTime.format(formatter)
    }

    fun saveValue(): Deferred<String> {
        setDate()
        val result = viewModelScope.async {
            repository.saveDataDiary(
                "patient_diary",
                hashMapData()
            )
        }
        return result
    }


}
