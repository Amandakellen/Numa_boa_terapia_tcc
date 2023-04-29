package com.example.numaboaterapia.appNavigation.pacient.views.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.numaboaterapia.appNavigation.pacient.data.DiaryItensEnum
import com.example.numaboaterapia.databinding.AdapterPatientInformationListBinding
import com.example.numaboaterapia.databinding.DiaryItemBinding
import com.example.numaboaterapia.register.data.RegisterResponseEnum
import com.example.numaboaterapia.register.view.adapter.RegisterResponseAdapter


class DiaryAdapter:
 RecyclerView.Adapter<DiaryAdapter.DiaryAdapterViewHolder>(){

     var data = ArrayList<DiaryItensEnum>()
     private lateinit var mListener: DiaryAdapter.onItemclickListener

    interface onItemclickListener {
        fun onItemClick(position: Int)
    }

    fun setOnClickListener(listener: onItemclickListener) {
        mListener = listener

    }

    inner class DiaryAdapterViewHolder(
        val binding: DiaryItemBinding,
        listener: DiaryAdapter.onItemclickListener
    ) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }



        fun bind(item: DiaryItensEnum) {

            with(binding) {
                feelingImageDiary.setImageResource(item.iconResource)
                feelingLabelDiary.setText(item.feelingName)
            }

        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DiaryAdapter.DiaryAdapterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DiaryItemBinding.inflate(layoutInflater, parent, false)

        return DiaryAdapterViewHolder(binding, mListener)
    }

    override fun onBindViewHolder(holder: DiaryAdapter.DiaryAdapterViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size
}