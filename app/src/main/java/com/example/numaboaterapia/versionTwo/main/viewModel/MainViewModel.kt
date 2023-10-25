package com.example.numaboaterapia.versionTwo.main.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.numaboaterapia.versionTwo.main.MainActions
import com.example.numaboaterapia.versionTwo.main.MainEffects
import com.example.numaboaterapia.versionTwo.main.MainStates
import com.example.numaboaterapia.extensions.MutableSingleLiveEvent

class MainViewModel(): ViewModel(), MainActions {

    private val _viewState = MutableLiveData<MainStates>()
    val viewState : LiveData<MainStates> = _viewState

    private val _uiEffect = MutableSingleLiveEvent<MainEffects>()
    val uiEffect = _uiEffect.asSingleLiveEvent()

    override fun sendAction(action: MainActions.Action) {
        when(action){
            MainActions.Action.ClickButton -> onClickButton()
        }
    }

    private fun onClickButton() {
        _uiEffect.post(MainEffects.NextScreen)
    }
}