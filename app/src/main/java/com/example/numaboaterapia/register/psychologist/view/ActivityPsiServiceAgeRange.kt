package com.example.numaboaterapia.register.psychologist.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.example.numaboaterapia.R
import com.example.numaboaterapia.databinding.ActivityPsiServiceAgeRangeBinding
import com.example.numaboaterapia.register.psychologist.viewModel.PsiAgeRangeViewModel
import com.example.numaboaterapia.register.view.adapter.RegisterResponseAdapter

class ActivityPsiServiceAgeRange : AppCompatActivity() {
    private lateinit var binding: ActivityPsiServiceAgeRangeBinding
    private var adapter = RegisterResponseAdapter()
    private lateinit var viewModel: PsiAgeRangeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPsiServiceAgeRangeBinding.inflate(layoutInflater)
        viewModel = PsiAgeRangeViewModel()

        setUpRecyclerview()
        setUpViews()

        setContentView(binding.root)
    }


    private fun setUpViews() {
        binding.ageRangeButton.setText(R.string.next)
        binding.psiAgeRangeToolbar.getBackButton().setOnClickListener {
            finish()
        }

        binding.psiAgeRangeToolbar.setStepText(R.string.psi_first_step)
        binding.psiAgeRangeToolbar.setTitleText(R.string.psi_first_title)

        binding.ageRangeButton.setOnClickListener {
            val result = viewModel.saveValue(adapter.itemSelected.joinToString(","))
            result.invokeOnCompletion {
                if (result.getCompleted() != "Sucesso") {
                    Toast.makeText(
                        applicationContext, "Ocorreu um erro, tente novamente!",
                        Toast.LENGTH_SHORT
                    ).show()

                } else {
                    startActivity(Intent(this, ActivityPsiSpecialties::class.java))
                }

            }

        }

        adapter.setOnClickListener(object : RegisterResponseAdapter.onItemclickListener {
            override fun onItemClick(position: Int) {

                adapter.addItem(resources.getString(adapter.data.get(position).feelingName))
                Toast.makeText(
                    applicationContext, adapter.itemSelected.joinToString(","),
                    Toast.LENGTH_SHORT
                ).show()

                binding.ageRangeButton.visibility = View.VISIBLE

            }
        })
    }

    private fun setUpRecyclerview() {

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