package com.example.numaboaterapia.versionTwo.main

interface MainActions {
    fun sendAction(action: Action)

    sealed class Action {
        object ClickButton : Action()
    }
}