package com.example.numaboaterapia.views.CustomView

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.numaboaterapia.R
import com.example.numaboaterapia.databinding.CheckBoxesGroupBinding

class CheckBoxesGroup(
    context: Context,
    attrs: AttributeSet
) : ConstraintLayout(context, attrs)  {

    private val view = View.inflate(context, R.layout.check_boxes_group, this)

    fun addCheckBox(id: Int){

    }
}