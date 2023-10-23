package com.example.numaboaterapia.compose.main

interface MainActions {
    fun sendAction(action: Action)

    sealed class Action {
        object ClickButton : Action()
    }
}