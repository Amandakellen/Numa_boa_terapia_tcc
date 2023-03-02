package com.example.numaboaterapia.register.pacient.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.numaboaterapia.databinding.ActivityPatientConsultationReasonBinding
import com.example.numaboaterapia.databinding.AdapterPatientInformationListBinding
import com.example.numaboaterapia.register.pacient.data.ConsultationReasonEnum

class ConsultationReasonAdapter :
    RecyclerView.Adapter<ConsultationReasonAdapter.ConsultationReasonViewHolder>() {

    var data = ArrayList<ConsultationReasonEnum>()

    inner class ConsultationReasonViewHolder(val binding: AdapterPatientInformationListBinding) :
        RecyclerView.ViewHolder(binding.root) {
       fun bind(item : ConsultationReasonEnum){

           with(binding){
               feelingImage.setImageResource(item.iconResource)
               feelingLabel.text = item.feelingName.toString()
           }

       }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ConsultationReasonViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = AdapterPatientInformationListBinding.inflate(layoutInflater,parent,false)
        return  ConsultationReasonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ConsultationReasonViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size
}