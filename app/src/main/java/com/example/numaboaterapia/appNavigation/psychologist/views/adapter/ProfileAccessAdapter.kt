package com.example.numaboaterapia.appNavigation.psychologist.views.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.numaboaterapia.databinding.ProfileAccessItemBinding

class ProfileAccessAdapter :
    RecyclerView.Adapter<ProfileAccessAdapter.ProfileAccessAdapterViewHolder>() {
    private lateinit var mListener: ProfileAccessAdapter.onItemclickListener
    var usersData =  ArrayList<HashMap<String, String>>()
    var averageData =  ArrayList<HashMap<String, String>>()

    interface onItemclickListener {
        fun onItemClick(position: Int)
    }

    fun setOnClickListener(listener: onItemclickListener) {
        mListener = listener

    }

    inner class ProfileAccessAdapterViewHolder(
        val binding: ProfileAccessItemBinding,
        listener: ProfileAccessAdapter.onItemclickListener,
        val context: Context
    ) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }


        fun bind(position: Int) {

            with(binding) {
                accessProfileName.text = usersData[position]["name"]
                accessProfileEmail.text = "Emal: "+ usersData[position]["email"]
                accessProfileRenda.text="Renda: " + averageData[position]["average"]
                accessDay.text = usersData[position]["day"]
                accesMounth.text = usersData[position]["mounth"]
                accessYear.text = usersData[position]["year"]


            }
        }


    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProfileAccessAdapter.ProfileAccessAdapterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ProfileAccessItemBinding.inflate(layoutInflater, parent, false)
        val context = parent.context

        return ProfileAccessAdapterViewHolder(binding, mListener, context)
    }

    override fun onBindViewHolder(
        holder: ProfileAccessAdapter.ProfileAccessAdapterViewHolder,
        position: Int
    ) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = usersData.size
}