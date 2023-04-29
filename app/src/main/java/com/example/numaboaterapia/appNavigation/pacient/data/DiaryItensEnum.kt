package com.example.numaboaterapia.appNavigation.pacient.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.numaboaterapia.R

enum class DiaryItensEnum (
    @DrawableRes val iconResource: Int,
    @StringRes val feelingName: Int
)  {
    AMOR(R.mipmap.amor, R.string.amor),
    FELICIDADE(R.mipmap.felicidade,R.string.felicidade),
    LEVEZA(R.mipmap.leveza,R.string.leveza),
    TRANQUILIDADE(R.mipmap.tranquilidade, R.string.tranquilidade),
    ANSIEDADE(R.mipmap.ansiedade, R.string.ansiedade_diary),
    TRISTEZA(R.mipmap.tristreza, R.string.tristeza),
    CANSACO(R.mipmap.cancaco,R.string.cansaco),
    IRRITACAO(R.mipmap.irritacao,R.string.irritacao),
    FRUSTRACAO(R.mipmap.frustracao, R.string.frustracao),
    ESTRESSE(R.mipmap.estresse, R.string.estresse),
    MEDO(R.mipmap.medo, R.string.medo_diary),
    VERGONHA(R.mipmap.vergonha, R.string.vergonha)
}