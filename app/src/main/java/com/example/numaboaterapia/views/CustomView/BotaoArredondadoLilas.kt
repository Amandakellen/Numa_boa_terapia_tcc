package com.example.numaboaterapia.views.CustomView

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.numaboaterapia.R

class BotaoArredondadoLilas(
    context: Context,
    attrs: AttributeSet
) : ConstraintLayout(context, attrs) {

    private val view = View.inflate(context, R.layout.layout_lilac_button, this)

    fun setText(text: Int){
        view.findViewById<TextView>(R.id.text_button_lilas).setText(text)

    }


}