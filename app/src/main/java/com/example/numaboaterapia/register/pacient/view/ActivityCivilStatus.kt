package com.example.numaboaterapia.register.pacient.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.numaboaterapia.R
import com.example.numaboaterapia.databinding.ActivityCivilStatusBinding
import com.example.numaboaterapia.register.view.adapter.RegisterResponseAdapter
import com.example.numaboaterapia.register.pacient.viewModel.PatientCivilStatusViewModel

class ActivityCivilStatus : AppCompatActivity() {

    private lateinit var binding: ActivityCivilStatusBinding
    private var adapter: RegisterResponseAdapter = RegisterResponseAdapter()
    private lateinit var viewModel: PatientCivilStatusViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCivilStatusBinding.inflate(layoutInflater)

        viewModel = PatientCivilStatusViewModel()


        setUpRecyclerview()
        setUpViews()

        setContentView(binding.root)
    }

    private fun setUpRecyclerview() {
        binding.civilStatusRecyclerview.layoutManager =
            GridLayoutManager(this, 2).also {
                it.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {

                       return if (adapter.itemCount % 2 == 0){
                             2
                        }else{
                            if (position!= adapter.itemCount-1){
                                 1
                            }else{
                                 2
                            }
                        }

                    }

                }

            }

        binding.civilStatusRecyclerview.adapter = adapter


        adapter.data = viewModel.setDataItens()
    }

    private fun setUpViews() {
        binding.civilStatusToolBar.setTitleText(R.string.second_title)
        binding.civilStatusToolBar.setStepText(R.string.second_step)
        binding.civilStatusToolBar.getBackButton().setOnClickListener {
            finish()
        }

        adapter.setOnClickListener(object : RegisterResponseAdapter.onItemclickListener {
            override fun onItemClick(position: Int) {
                val result = viewModel.saveValue(getString(adapter.data[position].feelingName))

                result.invokeOnCompletion {
                    if (result.getCompleted() != "Sucesso") {

                        Toast.makeText(
                            this@ActivityCivilStatus,
                            result.getCompleted(),
                            Toast.LENGTH_LONG
                        ).show()

                    } else {
                        startActivity(
                            Intent(
                                this@ActivityCivilStatus,
                                ActivityFinancialSituation::class.java
                            )
                        )
                    }

                }


            }
        })
    }
}