package com.example.numaboaterapia.compose.main

sealed class MainEffects {
    object NextScreen : MainEffects()
    data class NavigateToScreen(val screen: String) : MainEffects()
}