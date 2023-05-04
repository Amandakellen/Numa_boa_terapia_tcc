package com.example.numaboaterapia.appNavigation.pacient.views.adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.numaboaterapia.R
import com.example.numaboaterapia.databinding.HistoricDiaryItemBinding

class HistoricAdapter (val context: Context): RecyclerView.Adapter<HistoricAdapter.HistoricAdapterViewHolder>() {


    var dataFeelings = ArrayList<String>()
    var dataDate  = ArrayList<String>()
    var dataText = ArrayList<String>()

    inner class HistoricAdapterViewHolder(val binding: HistoricDiaryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(date : String, feeling: String, text :String) {

            with(binding){
                historicImage.setImageBitmap(setImage(feeling))
                emotionText.text= feeling
                historicText.text= text
                historicDate.text = date
            }
        }

    }

    private fun setImage(feeling : String): Bitmap {

        val icons = hashMapOf(
            "Amor" to R.mipmap.ic_lover_foreground,
            "Felicidade" to R.mipmap.ic_happy_foreground,
            "Leveza"  to R.mipmap.leveza_foreground,
            "Tranquilidade" to R.mipmap.tranquilidade_foreground,
            "Ansiedade" to R.mipmap.ansiedade_foreground,
            "Tristeza" to R.mipmap.tristreza_foreground,
            "Cansaço" to R.mipmap.cancaco_foreground,
            "Irritação" to R.mipmap.irritacao_foreground,
            "Frustração" to R.mipmap.frustracao_foreground,
            "Estresse" to R.mipmap.estresse_foreground,
            "Medo" to R.mipmap.medo_foreground,
            "Vergonha" to R.mipmap.vergonha_foreground
        )

        return icons[feeling]?.let { iconId ->
            val drawable = ContextCompat.getDrawable(context, iconId) as BitmapDrawable
            drawable.bitmap
        } ?: throw IllegalArgumentException("Ícone para a emoção $feeling não encontrado")

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoricAdapterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = HistoricDiaryItemBinding.inflate(layoutInflater, parent, false)

        return HistoricAdapterViewHolder(binding)
    }

    override fun getItemCount(): Int = dataDate.size

    override fun onBindViewHolder(holder: HistoricAdapterViewHolder, position: Int) {
        holder.bind(dataDate[position],dataFeelings[position], dataText[position])
    }


}