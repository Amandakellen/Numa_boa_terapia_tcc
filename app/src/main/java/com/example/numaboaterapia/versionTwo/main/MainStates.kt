package com.example.numaboaterapia.versionTwo.main

sealed class MainStates{
    data class Enabled(val isEnabled: Boolean) : MainStates()
    data class Disabled(val isEnabled: Boolean) : MainStates()
}
