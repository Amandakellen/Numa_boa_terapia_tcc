package com.example.numaboaterapia.views.CustomView

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.numaboaterapia.R

class PatientInformationToolBarcontext(
    context: Context,
    attrs: AttributeSet
) : ConstraintLayout(context, attrs) {

    private val view = View.inflate(context, R.layout.tool_bar_patient_information, this)
    private val backButton = view.findViewById<ImageButton>(R.id.patient_back_button)
    private val title = view.findViewById<TextView>(R.id.tiltle)
    private val step = view.findViewById<TextView>(R.id.step_number)

    fun getBackButton(): ImageButton = backButton

    fun setTitleText(text : String) = text.also { this.title.text = it }

    fun setStepText(text : String) = text.also { this.step.text = it }
}