package com.example.numaboaterapia.views.CustomView

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.CheckBox
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import com.example.numaboaterapia.R

class CheckBoxes(
    context: Context,
    attrs: AttributeSet
) : ConstraintLayout(context, attrs) {

    private val view = View.inflate(context, R.layout.check_boxes_custom, this)
    fun setViews(size: Int){
        var lastId = -1
        for(i in 1..size){
            var checkBox = CheckBox(context)
            checkBox.setText("Entry"+i)
            checkBox.setTextColor(ContextCompat.getColor(context, R.color.gray))
            val id = CheckBox.generateViewId()
            checkBox.id = id
            val constraint = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.WRAP_CONTENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            )

            constraint.startToStart = ConstraintSet.PARENT_ID
            constraint.endToEnd = ConstraintSet.PARENT_ID

            if (i==0){
                constraint.topToTop = ConstraintSet.PARENT_ID
            }else{
                constraint.topToBottom = lastId
            }
            checkBox.layoutParams = constraint

            view.apply {
                addView(checkBox)
            }
            lastId = id

        }

    }
}


