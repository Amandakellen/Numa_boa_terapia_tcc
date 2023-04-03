package com.example.numaboaterapia.register.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.numaboaterapia.R

enum class RegisterResponseEnum(
    @DrawableRes val iconResource: Int,
    @StringRes val feelingName: Int
) {
    ANXIETY(R.mipmap.ic_ansiety_new, R.string.anxiety),
    PERSONAL_GROWTH(R.mipmap.ic_personal_growth_simbol, R.string.personal_growth),
    DEPRESSION(R.mipmap.ic_depression_simbol, R.string.depression),
    GRIEF(R.mipmap.ic_grief_simbol, R.string.grief),
    COUPLE(R.mipmap.ic_couple_simbol, R.string.couple),
    EATING_DISORDER(R.mipmap.ic_eating_disorder_simbol, R.string.eating_disorder),
    SEXUALITY(R.mipmap.ic_sexuality_simbol, R.string.sexuality),
    OTHER(R.mipmap.ic_other_simbol, R.string.other),
    SINGLE(R.mipmap.ic_single, R.string.single),
    RELATIONSHIP(R.mipmap.ic_relationship, R.string.relationship),
    DIVORCED(R.mipmap.ic_divorced, R.string.divorced),
    WIDOWER(R.mipmap.ic_widower, R.string.widower),
    DONT_INFORM(R.mipmap.ic_dont_inform, R.string.dont_inform),
    EXCELLENT(R.mipmap.ic_excellent_icon, R.string.excellent),
    GOOD(R.mipmap.ic_good_icon, R.string.good),
    STABLE(R.mipmap.ic_stable_icon, R.string.stable),
    BAD(R.mipmap.ic_bad_icon, R.string.bad),
    DONT_KNOW(R.mipmap.ic_dont_know_icon, R.string.dont_know),
    CHILDREN(R.mipmap.ic_child,R.string.children),
    TEENAGERS(R.mipmap.ic_teenager,R.string.teenagers),
    ADULTS(R.mipmap.ic_adults,R.string.adults),
    ELDERLY(R.mipmap.ic_elderly,R.string.elderly),
    COUPLES(R.mipmap.ic_couples,R.string.couples)

}