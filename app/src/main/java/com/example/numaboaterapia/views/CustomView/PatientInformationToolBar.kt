package com.example.numaboaterapia.views.CustomView

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageButton
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.numaboaterapia.R

class PatientInformationToolBar(
    context: Context,
    attrs: AttributeSet
) : FrameLayout(context, attrs) {

    private val view = View.inflate(context, R.layout.tool_bar_patient_information, this)
    private val backButton = view.findViewById<ImageButton>(R.id.patient_back_button)
    val title = view.findViewById<TextView>(R.id.tiltle)
    val step = view.findViewById<TextView>(R.id.step_number)

    fun getBackButton(): ImageButton = backButton

    fun setTitleText(text : Int) = title.setText(text)

    fun setStepText(text : Int) = step.setText(text)
}