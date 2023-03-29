package com.example.numaboaterapia.views.CustomView

import android.content.Context
import android.util.AttributeSet
import android.util.Log
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
    private var specialties = ArrayList<PsiSpecialtiesEnum>()
    var idsList = ArrayList<Int>()
    var specialtiesChecked = ArrayList<String>()

    fun setSpecialties(itens: ArrayList<PsiSpecialtiesEnum>) {
        specialties = ArrayList<PsiSpecialtiesEnum>()
        specialties = itens
    }

    private fun checkSize(count : Int){
        if(count == 20){
            disabled()
        }else{
            enabled()
        }
    }

     fun changeVisibilitySearch(searchItens : ArrayList<String>){
         var checkBox: CheckBox
         idsList.forEach {itSpecialties->
             checkBox =findViewById<CheckBox>(itSpecialties)
            if(!searchItens.contains(checkBox.text)){
                checkBox.visibility = INVISIBLE
            }else{
                checkBox.visibility = VISIBLE
            }
        }
    }

    fun visibilityAll(){
        idsList.forEach {
            findViewById<CheckBox>(it).visibility = VISIBLE
        }
    }

    private fun disabled(){
        idsList.forEach {
        var checkBox = findViewById<CheckBox>(it)
            if(!specialtiesChecked.contains( checkBox.text)){
                checkBox.isEnabled = false
            }
        }
    }
    private fun enabled(){
        idsList.forEach {
            findViewById<CheckBox>(it).isEnabled = true
        }
    }

    fun setSpecialtiesViews() {
        var lastId = -1
        var count = 0
        for (i in specialties) {
            var checkBox = CheckBox(context)
            checkBox.setText(i.specialtiesName)
            checkBox.setTextColor(ContextCompat.getColor(context, R.color.gray))
            val id = CheckBox.generateViewId()
            checkBox.buttonTintList = ContextCompat.getColorStateList(context, R.color.light_purple)
            checkBox.id = id

            checkBox.setOnCheckedChangeListener { buttonView, isChecked ->

                if (isChecked){
                    checkBox.buttonTintList = ContextCompat.
                    getColorStateList(context, R.color.light_purple)
                    if(!specialtiesChecked.contains(resources.getString(i.specialtiesName))
                        && count!=20){
                        count++
                        specialtiesChecked.add(resources.getString(i.specialtiesName))
                        checkSize(count)
                    }

                }else{
                    if(specialtiesChecked.contains(resources.getString(i.specialtiesName))){
                        count--
                        specialtiesChecked.remove(resources.getString(i.specialtiesName))
                        enabled()
                    }

                }


            }

            val constraint = ConstraintLayout.LayoutParams(
                ConstraintLayout.LayoutParams.MATCH_PARENT,
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            )

            constraint.startToStart = ConstraintSet.PARENT_ID
            constraint.endToEnd = ConstraintSet.PARENT_ID

            if (i == PsiSpecialtiesEnum.GRAVIDEZ) {
                constraint.topToTop = ConstraintSet.PARENT_ID
            } else {
                constraint.topToBottom = lastId
            }
            checkBox.layoutParams = constraint

            view.apply {
                addView(checkBox)
            }
            idsList.add(id)
            lastId = id

        }

    }

}


