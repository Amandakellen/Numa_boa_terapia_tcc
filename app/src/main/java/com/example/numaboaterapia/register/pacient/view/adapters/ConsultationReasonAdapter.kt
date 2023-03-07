package com.example.numaboaterapia.register.pacient.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.numaboaterapia.databinding.AdapterPatientInformationListBinding
import com.example.numaboaterapia.register.pacient.data.ConsultationReasonEnum


class ConsultationReasonAdapter :
    RecyclerView.Adapter<ConsultationReasonAdapter.ConsultationReasonViewHolder>() {

    var data = ArrayList<ConsultationReasonEnum>()
    private lateinit var mListener: onItemclickListener

    interface onItemclickListener {
        fun onItemClick(position: Int)
    }

    fun setOnClickListener(listener: onItemclickListener) {
        mListener = listener
    }


    inner class ConsultationReasonViewHolder(
        val binding: AdapterPatientInformationListBinding,
        listener: onItemclickListener
    ) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }


        fun bind(item: ConsultationReasonEnum) {

            with(binding) {
                feelingImage.setImageResource(item.iconResource)
                feelingLabel.setText(item.feelingName)
            }

        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ConsultationReasonViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = AdapterPatientInformationListBinding.inflate(layoutInflater, parent, false)

        return ConsultationReasonViewHolder(binding, mListener)
    }

    override fun onBindViewHolder(holder: ConsultationReasonViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size
}