package com.example.numaboaterapia.appNavigation.pacient.viewModel

import android.content.Context
import android.net.Uri
import android.provider.MediaStore
import androidx.core.content.FileProvider
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.storage.FirebaseStorage
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class PhotoViewModel : ViewModel() {
    private val _photoUri = MutableLiveData<Uri?>()
    private lateinit var currentPhotoPath: String


}