package com.example.numaboaterapia.views.CustomView

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.CheckBox
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import com.example.numaboaterapia.R
import com.example.numaboaterapia.register.psychologist.data.PsiSpecialtiesEnum

class CheckBoxes(
    context: Context,
    attrs: AttributeSet
) : ConstraintLayout(context, attrs) {

    private val view = View.inflate(context, R.layout.check_boxes_custom, this)
    private var specialties =  ArrayList<PsiSpecialtiesEnum>()

    fun setSpecialties(itens : ArrayList<PsiSpecialtiesEnum>){
        specialties = itens
    }

    fun setSpecialtiesViews(){
        var lastId = -1
        for(i in specialties){
            var checkBox = CheckBox(context)
            checkBox.setText(i.specialtiesName)
            checkBox.setTextColor(ContextCompat.getColor(context, R.color.gray))
            val id = CheckBox.generateViewId()
            checkBox.buttonTintList= ContextCompat.getColorStateList(context, R.color.light_purple)
            checkBox.id = id
            val constraint = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            )

            constraint.startToStart = ConstraintSet.PARENT_ID
            constraint.endToEnd = ConstraintSet.PARENT_ID

            if (i==PsiSpecialtiesEnum.GRAVIDEZ){
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


