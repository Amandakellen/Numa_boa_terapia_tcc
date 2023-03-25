package com.example.numaboaterapia.register.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.numaboaterapia.databinding.SpecialitiesItemLayoutBinding
import com.example.numaboaterapia.register.data.RegisterResponseEnum
import com.example.numaboaterapia.register.psychologist.data.PsiSpecialtiesEnum


class PsiSpecialtiesAdapter(private val context : Context) :
    RecyclerView.Adapter<PsiSpecialtiesAdapter.PsiSpecialtiesAdapterViewHolder>() {

    var data = ArrayList<PsiSpecialtiesEnum>()
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


     class PsiSpecialtiesAdapterViewHolder(
        val binding: SpecialitiesItemLayoutBinding,
        listener: onItemclickListener
    ) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }



        fun bind(item: PsiSpecialtiesEnum) {

            with(binding) {
                checkBoxSpecialties.setText(item.specialtiesName)
            }

        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PsiSpecialtiesAdapterViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val binding = SpecialitiesItemLayoutBinding.inflate(layoutInflater, parent, false)

        return PsiSpecialtiesAdapterViewHolder(binding, mListener)
    }

    override fun onBindViewHolder(holder: PsiSpecialtiesAdapterViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size
}