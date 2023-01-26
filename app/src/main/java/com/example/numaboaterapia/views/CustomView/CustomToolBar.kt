package com.example.numaboaterapia.views.CustomView

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.numaboaterapia.R

class CustomToolBar(
    context: Context,
    attrs: AttributeSet
) : ConstraintLayout(context, attrs) {

    private val view = View.inflate(context, R.layout.tool_bar_layout, this)
    private val backButton = view.findViewById<ImageButton>(R.id.back_button)

    fun getBackButton(): ImageButton = backButton
}