package com.example.numaboaterapia.appNavigation.psychologist.views.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.numaboaterapia.databinding.PatientsListItemBinding


class PsiPatientAdapter : RecyclerView.Adapter<PsiPatientAdapter.PsiPatientAdapterViewHolder>() {
    private lateinit var mListener: onItemClickListener
    var usersData = ArrayList<HashMap<String, String>>()
    var averageData = ArrayList<HashMap<String, String>>()

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }

    inner class PsiPatientAdapterViewHolder(private val binding: PatientsListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                mListener.onItemClick(adapterPosition)
            }
        }

        fun bind(position: Int) {
            val userData = usersData[position]
            val averageDataItem = averageData[position]


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PsiPatientAdapterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = PatientsListItemBinding.inflate(layoutInflater, parent, false)
        return PsiPatientAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PsiPatientAdapterViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = usersData.size

    fun filterByDate(selectedDate: String) {
        val filteredData = ArrayList<HashMap<String, String>>()
        for (i in 0 until usersData.size) {
            val user = usersData[i]
            val userDate = "${user["day"]}/${user["mounth"]}/${user["year"]}"
            if (userDate == selectedDate) {
                filteredData.add(user)
            }
        }
        usersData = filteredData
        notifyDataSetChanged()
    }
}
