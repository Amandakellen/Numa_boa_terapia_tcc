package com.example.numaboaterapia.appNavigation.psychologist.views.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.numaboaterapia.databinding.ProfileAccessItemBinding

class ProfileAccessAdapter : RecyclerView.Adapter<ProfileAccessAdapter.ProfileAccessAdapterViewHolder>() {
    private lateinit var mListener: onItemClickListener
    var usersData = ArrayList<HashMap<String, String>>()
    var averageData = ArrayList<HashMap<String, String>>()

    interface onItemClickListener {
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener) {
        mListener = listener
    }

    inner class ProfileAccessAdapterViewHolder(private val binding: ProfileAccessItemBinding) : RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                mListener.onItemClick(adapterPosition)
            }
        }

        fun bind(position: Int) {
            val userData = usersData[position]
            val averageDataItem = averageData[position]

            binding.accessProfileName.text = userData["name"]
            binding.accessProfileEmail.text = "Email: " + userData["email"]
            binding.accessProfileRenda.text = "Renda: " + averageDataItem["average"]
            binding.accessDay.text = userData["day"]
            binding.accesMounth.text = userData["mounth"]
            binding.accessYear.text = userData["year"]
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileAccessAdapterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ProfileAccessItemBinding.inflate(layoutInflater, parent, false)
        return ProfileAccessAdapterViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProfileAccessAdapterViewHolder, position: Int) {
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
