package com.example.numaboaterapia.register.psychologist.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.example.numaboaterapia.R
import com.example.numaboaterapia.databinding.ActivityPsiServiceAgeRangeBinding
import com.example.numaboaterapia.register.psychologist.viewModel.PsiAgeRangeViewModel
import com.example.numaboaterapia.register.view.adapter.RegisterResponseAdapter

class ActivityPsiServiceAgeRange : AppCompatActivity() {
    private lateinit var binding:ActivityPsiServiceAgeRangeBinding
    private var adapter = RegisterResponseAdapter()
    private lateinit var viewModel : PsiAgeRangeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =  ActivityPsiServiceAgeRangeBinding.inflate(layoutInflater)
        viewModel =  PsiAgeRangeViewModel()

        setUpRecyclerview()
        setUpViews()

        setContentView(binding.root)
    }

    private fun setUpViews(){
        binding.psiAgeRangeToolbar.getBackButton().setOnClickListener {
            finish()
        }

        binding.psiAgeRangeToolbar.setStepText(R.string.psi_first_step)
        binding.psiAgeRangeToolbar.setTitleText(R.string.psi_first_title)

        adapter.setOnClickListener(object : RegisterResponseAdapter.onItemclickListener {
            override fun onItemClick(position: Int) {
                Log.i("tag","teste")
//                val result = viewModel.saveValue(getString(adapter.data[position].feelingName))
//
//                result.invokeOnCompletion {
//                    if (result.getCompleted() != "Sucesso") {
//                        //todo
//
//                    } else {
//                        //todo
//                    }
//
//                }


            }
        })
    }

    fun setUpRecyclerview() {

        binding.psiAgeRangeRecyclerview.layoutManager =
            GridLayoutManager(this, 2).also {
                it.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                    override fun getSpanSize(position: Int): Int {
                        return if (adapter.itemCount % 2 == 0) {
                            2
                        } else {
                            if (position != adapter.itemCount - 1) {
                                1
                            } else {
                                2
                            }
                        }
                    }

                }

            }

        binding.psiAgeRangeRecyclerview.adapter = adapter
        adapter.data = viewModel.setDataItens()
    }
}