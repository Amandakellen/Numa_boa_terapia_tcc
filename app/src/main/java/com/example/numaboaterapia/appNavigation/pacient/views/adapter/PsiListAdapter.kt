package com.example.numaboaterapia.appNavigation.pacient.views.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.numaboaterapia.databinding.PsiListItemBinding

class PsiListAdapter : RecyclerView.Adapter<PsiListAdapter.PsiListAdapterViewHolder>() {

    private lateinit var mListener: PsiListAdapter.onItemclickListener
    var psiUser: ArrayList<HashMap<String, String>> = ArrayList()
    var biography: ArrayList<HashMap<String, String>> = ArrayList()

    interface onItemclickListener {
        fun onItemClick(position: Int)
    }

    fun setOnClickListener(listener: onItemclickListener) {
        mListener = listener

    }

    inner class PsiListAdapterViewHolder(
        val binding: PsiListItemBinding,
        listener: PsiListAdapter.onItemclickListener
    ) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.pasiListItemButtom.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }


        fun bind(position: Int) {
            with(binding) {
                psiListItemName.text = psiUser[position]["name"]
//                psiListItemLocation.text =
//                    biography[position]["city"] + " - " + biography[position]["uf"]
                psiListItemCrp.text = psiUser[position]["crp"]
                psiListItemEspecializacao.text= psiUser[position]["especializacao"]
                psiListTime.text = "Duração da sessão: "+ psiUser[position]["time"] + " min"
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PsiListAdapter.PsiListAdapterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = PsiListItemBinding.inflate(layoutInflater, parent, false)

        return PsiListAdapterViewHolder(binding, mListener)
    }

    override fun onBindViewHolder(holder: PsiListAdapter.PsiListAdapterViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = psiUser.size


}