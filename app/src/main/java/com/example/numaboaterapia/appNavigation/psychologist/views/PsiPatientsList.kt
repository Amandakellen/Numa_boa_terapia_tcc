package com.example.numaboaterapia.appNavigation.psychologist.views

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.numaboaterapia.R
import com.example.numaboaterapia.appNavigation.psychologist.viewmodel.PsiPatientListViewModel
import com.example.numaboaterapia.appNavigation.psychologist.views.adapter.PsiPatientAdapter
import com.example.numaboaterapia.databinding.ActivityPsiPatientsListBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.ByteArrayOutputStream
import java.io.InputStream
import java.lang.Exception


class PsiPatientsList : AppCompatActivity() {
    private lateinit var binding : ActivityPsiPatientsListBinding
    private lateinit var viewModel : PsiPatientListViewModel
    private var patientData =  ArrayList<HashMap<String, String>>()
    private var adapter = PsiPatientAdapter()
    private var patientImage = mutableListOf<ByteArray?>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPsiPatientsListBinding.inflate(layoutInflater)
        viewModel = PsiPatientListViewModel()
        setUpViews()
        setUpRecyclerView()
        setContentView(binding.root)
    }



    @SuppressLint("ResourceType")
    private fun setUpViews(){
        binding.psiPatientListToolBar.getBackButton().setOnClickListener {
            finish()
        }
        binding.psiPatientListToolBar.setTitleText(R.string.patient_list)
        binding.floatingActionButton.setOnClickListener {
            startActivity(Intent(this, ProfileAccesses::class.java))
        }
        val inputStream = this.resources?.openRawResource(R.mipmap.pacient_gray)
        val byteArray = inputStream?.let { fileToByteArray(it) }
        if (byteArray != null) {
            viewModel.setImageDefault(byteArray)
        }
        binding.patientListRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.patientListRecyclerView.adapter = adapter

        binding.psiPatientTextInputEdit.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s.isNullOrEmpty()) {
                    adapter.usersData = patientData
                    adapter.patientImage = patientImage
                    adapter.notifyDataSetChanged()
                }else{
                    if (s?.length!! >= 3) {
                        viewModel.setSearchText(s.toString())
                        viewModel.filterPatientDataAndImages()
                    }

                }

            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun hideViews(){
        with(binding){
            patientListProgress.visibility = View.VISIBLE
            floatingActionButton.visibility= View.GONE
            patientListCount.visibility = View.GONE
            patientListLine.visibility = View.GONE
            patientListScrollView.visibility = View.GONE
        }
    }

    private fun showViews(){
        with(binding){
            patientListProgress.visibility = View.GONE
            floatingActionButton.visibility= View.VISIBLE
            patientListCount.visibility = View.VISIBLE
            patientListLine.visibility = View.VISIBLE
            patientListScrollView.visibility = View.VISIBLE
        }
    }
    private fun setUpRecyclerView(){
        hideViews()
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.getPatientCollection()
            viewModel.getImage()
            withContext(Dispatchers.Main) {
                patientData = viewModel.getPatientList()
                patientImage = viewModel.getImageData().toMutableList()
                showViews()
                adapter.usersData = patientData
                adapter.patientImage = patientImage
                if(patientData.size == 1){
                    binding.patientListCount.text = "1 paciente encontrado"
                }else{
                    binding.patientListCount.text =
                        patientData.size.toString() + " pacientes encontrados"
                }



                adapter.setOnItemClickListener(object : PsiPatientAdapter.onItemClickListener {
                    override fun onItemClick(position: Int) {
                        val intent = Intent(applicationContext, PatientRecord::class.java)
                        intent.putExtra("name", adapter.usersData[position]["patient_name"])
                        intent.putExtra("wpp", adapter.usersData[position]["patient_wpp"])
                        intent.putExtra("cpf",adapter.usersData[position]["patient_cpf"])

                        intent.putExtra("image", patientImage[position])
                        startActivity(intent)

                    }
                })
            }
        }
        viewModel.searchText.observe(this) { filteredUsersData ->
            adapter.usersData = viewModel.getFilteredPatientData()
            adapter.patientImage = viewModel.getFilteredPatientImage().toMutableList()
            adapter.notifyDataSetChanged()

        }

    }

    fun fileToByteArray(inputStream: InputStream): ByteArray {
        val buffer = ByteArray(1024)
        val byteArrayOutputStream = ByteArrayOutputStream()

        var length: Int
        while (inputStream.read(buffer).also { length = it } != -1) {
            byteArrayOutputStream.write(buffer, 0, length)
        }

        inputStream.close()
        byteArrayOutputStream.flush()

        return byteArrayOutputStream.toByteArray()
    }
}