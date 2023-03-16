package com.example.numaboaterapia.register.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.numaboaterapia.R
import com.example.numaboaterapia.databinding.AdapterPatientInformationListBinding
import com.example.numaboaterapia.register.data.RegisterResponseEnum


class RegisterResponseAdapter :
    RecyclerView.Adapter<RegisterResponseAdapter.PatientResponseAdapterViewHolder>() {

    var data = ArrayList<RegisterResponseEnum>()
    private lateinit var mListener: onItemclickListener
    var itemSelected = ArrayList<String>()

    interface onItemclickListener {
        fun onItemClick(position: Int)
    }

    fun setOnClickListener(listener: onItemclickListener) {
        mListener = listener

    }

    fun addItem(item: String) {
        if (!itemSelected.contains(item)) {
            itemSelected.add(item)


        } else {
            itemSelected.remove(item)
        }
    }


    inner class PatientResponseAdapterViewHolder(
        val binding: AdapterPatientInformationListBinding,
        listener: onItemclickListener
    ) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }



        fun bind(item: RegisterResponseEnum) {

            with(binding) {
                feelingImage.setImageResource(item.iconResource)
                feelingLabel.setText(item.feelingName)
            }

        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PatientResponseAdapterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = AdapterPatientInformationListBinding.inflate(layoutInflater, parent, false)

        return PatientResponseAdapterViewHolder(binding, mListener)
    }

    override fun onBindViewHolder(holder: PatientResponseAdapterViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size
}