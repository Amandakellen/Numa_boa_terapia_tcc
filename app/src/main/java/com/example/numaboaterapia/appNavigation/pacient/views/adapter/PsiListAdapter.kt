package com.example.numaboaterapia.appNavigation.pacient.views.adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.numaboaterapia.R
import com.example.numaboaterapia.databinding.PsiListItemBinding
import java.io.ByteArrayOutputStream

class PsiListAdapter : RecyclerView.Adapter<PsiListAdapter.PsiListAdapterViewHolder>() {

    private lateinit var mListener: PsiListAdapter.onItemclickListener
    var psiUser: ArrayList<HashMap<String, String>> = ArrayList()
    var biography: ArrayList<HashMap<String, String>> = ArrayList()
    var psiImage = mutableListOf<ByteArray>()
    private val typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD)

    private fun convertImageToByteArray(context: Context, resourceId: Int): ByteArray {
        val bitmap = BitmapFactory.decodeResource(context.resources, resourceId)
        val outputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
        return outputStream.toByteArray()
    }
    interface onItemclickListener {
        fun onItemClick(position: Int)
    }

    fun setOnClickListener(listener: onItemclickListener) {
        mListener = listener

    }

    inner class PsiListAdapterViewHolder(
        val binding: PsiListItemBinding,
        listener: onItemclickListener,
        val context: Context
    ) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.pasiListItemButtom.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }


        fun bind(position: Int) {
            val bitmap = BitmapFactory.decodeByteArray(psiImage[position], 0, psiImage[position].size)


            with(binding) {
                psiListItemName.text = psiUser[position]["name"]
                psiListItemLocation.text =
                    biography[position]["city"] + " - " + biography[position]["uf"]
                psiListItemCrp.text = psiUser[position]["crp"]
                psiListItemEspecializacao.text = psiUser[position]["especializacao"]
                psiListTime.text = "Duração da sessão: " + psiUser[position]["time"] + " minutos"
                psiListTime.setTypeface(typeface)
                if(bitmap==null){
                    psiListPhoto.setImageResource(R.mipmap.psi_gray)
                }else{
                    psiListPhoto.setImageBitmap(bitmap)
                }

            }
        }


    }





    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PsiListAdapter.PsiListAdapterViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = PsiListItemBinding.inflate(layoutInflater, parent, false)
        val context = parent.context

        return PsiListAdapterViewHolder(binding, mListener, context)
    }

    override fun onBindViewHolder(holder: PsiListAdapter.PsiListAdapterViewHolder, position: Int) {
        holder.bind(position)
    }

    override fun getItemCount(): Int = psiUser.size


}