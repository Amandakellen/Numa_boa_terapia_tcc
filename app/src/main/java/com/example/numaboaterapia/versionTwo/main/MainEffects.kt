package com.example.numaboaterapia.versionTwo.main

sealed class MainEffects {
    object NextScreen : MainEffects()
    data class NavigateToScreen(val screen: String) : MainEffects()
}