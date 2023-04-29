package com.example.numaboaterapia.appNavigation.pacient.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.numaboaterapia.R
import com.example.numaboaterapia.appNavigation.pacient.viewModel.EmocionalDiaryViewModel
import com.example.numaboaterapia.appNavigation.pacient.views.adapter.DiaryAdapter
import com.example.numaboaterapia.databinding.ActivityEmocionalDiaryBinding
import com.example.numaboaterapia.register.view.adapter.RegisterResponseAdapter

class EmocionalDiaryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEmocionalDiaryBinding
    private lateinit var viewModel: EmocionalDiaryViewModel
    private var adapter = DiaryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityEmocionalDiaryBinding.inflate(layoutInflater)
        viewModel = EmocionalDiaryViewModel()
        setToolBar()
        setRecyclerView()
        setContentView(binding.root)
    }

    private fun setToolBar(){
        binding.patientEmotionalDiaryToolBar.setTitleText(R.string.feelings)
        binding.patientEmotionalDiaryToolBar.getBackButton().setOnClickListener {
            finish()
        }

    }

    private fun setUpAdapter(){
        adapter.data = viewModel.setDataItens()
        val intent = Intent(this, RegisterEmotionActivity::class.java)

        adapter.setOnClickListener(object : DiaryAdapter.onItemclickListener {
            override fun onItemClick(position: Int) {

                val item = resources.getString(adapter.data.get(position).feelingName)

                intent.putExtra("itemSelected",item)
                startActivity(intent)


            }
        })
    }

    private fun setRecyclerView(){
        binding.recycleviewDiary.layoutManager = GridLayoutManager(this, 3)
        binding.recycleviewDiary.adapter = adapter

        setUpAdapter()
    }

}