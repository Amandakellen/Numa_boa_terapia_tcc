package com.example.numaboaterapia.appNavigation.pacient.views.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.numaboaterapia.appNavigation.pacient.data.DiaryItensEnum
import com.example.numaboaterapia.databinding.DiaryItemBinding
import com.example.numaboaterapia.databinding.HistoricDiaryItemBinding

class HistoricAdapter : RecyclerView.Adapter<HistoricAdapter.HistoricAdapterViewHolder>() {

    var data = HashMap<Int, Any>()

    inner class HistoricAdapterViewHolder(val binding: HistoricDiaryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: HashMap<Int, Any>) {

            with(binding) {
//                feelingImageDiary.setImageResource(item.iconResource)
//                feelingLabelDiary.setText(item.feelingName)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoricAdapterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = HistoricDiaryItemBinding.inflate(layoutInflater, parent, false)

        return HistoricAdapterViewHolder(binding)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: HistoricAdapterViewHolder, position: Int) {
        holder.bind(data[position] as HashMap<Int, Any>)
    }


}