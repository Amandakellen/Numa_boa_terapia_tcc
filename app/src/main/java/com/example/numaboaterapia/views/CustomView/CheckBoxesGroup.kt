package com.example.numaboaterapia.views.CustomView

import android.content.Context
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.marginStart
import com.example.numaboaterapia.R


class CheckBoxesGroup(
    context: Context,
    attrs: AttributeSet
) : ConstraintLayout(context, attrs)  {

    private val view = View.inflate(context, R.layout.check_boxes_group, this)

    fun addCheckBox(text: Int, id: Int){
        val checkBox = CheckBox(view.context)
        checkBox.setHint(text)
        checkBox.layoutParams = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        checkBox.setPadding(20, 20, 20, 20)

        checkBox.id = id
        checkBox.setText(text)
        checkBox.setTextColor(ContextCompat.getColor(context, R.color.gray))
        checkBox.buttonTintList= ContextCompat.getColorStateList(context, R.color.light_purple)

        view.apply {
            addView(checkBox)

        }
    }
}