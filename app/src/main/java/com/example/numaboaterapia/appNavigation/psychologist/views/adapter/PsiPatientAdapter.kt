package com.example.numaboaterapia.appNavigation.psychologist.views.adapter

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.numaboaterapia.R
import com.example.numaboaterapia.databinding.PatientsListItemBinding


class PsiPatientAdapter : RecyclerView.Adapter<PsiPatientAdapter.PsiPatientAdapterViewHolder>() {
    private lateinit var mListener: onItemClickListener
    var usersData = ArrayList<HashMap<String, String>>()
    var patientImage = mutableListOf<ByteArray?>()


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

            with(binding){
                patientListItemName.text = usersData[position]["patient_name"]
                patientListItemCpf.text = "CPF: "+ usersData[position]["patient_cpf"]
                patientListItemWpp.text = "Whatsapp: "+usersData[position]["patient_wpp"]

                if (position < patientImage.size && patientImage[position] != null) {
                    val bitmap =
                        patientImage[position]?.let {
                            BitmapFactory.decodeByteArray(patientImage[position],
                                0, it.size)
                        }
                    patientListPhoto.setImageBitmap(bitmap)
                }else{
                    patientListPhoto.setImageResource(R.mipmap.pacient_gray)
                }

//                try{
//                    val bitmap =
//                        patientImage[position]?.let {
//                            BitmapFactory.decodeByteArray(patientImage[position],
//                                0, it.size)
//                        }
//                    patientListPhoto.setImageBitmap(bitmap)
//                }catch (e:Exception){
//                    patientListPhoto.setImageResource(R.mipmap.pacient_gray)
//                }

            }



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

}
