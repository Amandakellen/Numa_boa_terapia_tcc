package com.example.numaboaterapia.register.psychologist.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import com.example.numaboaterapia.R
import com.example.numaboaterapia.databinding.ActivityPsiSpecialtiesBinding
import com.example.numaboaterapia.register.psychologist.viewModel.PsiSpecialtiesViewModel

class ActivityPsiSpecialties : AppCompatActivity() {
    private lateinit var binding: ActivityPsiSpecialtiesBinding
    private lateinit var viewModel: PsiSpecialtiesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPsiSpecialtiesBinding.inflate(layoutInflater)
        viewModel = PsiSpecialtiesViewModel()

        setUpViews()
        setUpCheckBoxes()
        setUpSearch()
        setContentView(binding.root)
    }

    private fun setUpViews() {
        binding.specialtiesButton.setText(R.string.next)
        binding.psiSpecialtiesToolBar.getBackButton().setOnClickListener {
            finish()
        }

        binding.psiSpecialtiesToolBar.setStepText(R.string.psi_second_step)
        binding.psiSpecialtiesToolBar.setTitleText(R.string.psi_second_title)

        binding.specialtiesButton.setOnClickListener {
            if (!binding.specialtiesCheckboxGroup.specialtiesChecked.isNullOrEmpty()) {
                val result = viewModel.saveValue()
                result.invokeOnCompletion {
                    if (result.getCompleted() != "Sucesso") {
                        Toast.makeText(
                            applicationContext, "Ocorreu um erro, tente novamente!",
                            Toast.LENGTH_SHORT
                        ).show()

                    } else {
                        startActivity(Intent(this, PsiBiography::class.java))
                    }
                }
            } else {
                Toast.makeText(
                    this, "Selecione no mínimo 1 item", Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun setUpSearch() {
        val itens = viewModel.getSpecialties()

        binding.specialitiesTextInputEdit.doOnTextChanged { text, start, before, count ->
            viewModel.setSearch(text.toString())
        }
        binding.specialitiesTextInputEdit.doAfterTextChanged {
            var itensSearch = ArrayList<String>()

            if (!viewModel.searchIsNullOrEmpty()) {
                if (viewModel.searchSize()!! >= 3) {

                    itens.forEach { iterator ->
                        if (resources.getString(iterator.specialtiesName).lowercase()
                                .contains(viewModel.getSearch())
                        ) {
                            itensSearch.add(resources.getString(iterator.specialtiesName))

                        }
                    }
                    binding.specialtiesCheckboxGroup.changeVisibilitySearch(itensSearch)


                }
            } else {
                binding.specialtiesCheckboxGroup.visibilityAll()
            }

        }

        viewModel.setItensSelected(binding.specialtiesCheckboxGroup.specialtiesChecked)

    }

    private fun setUpCheckBoxes() {
        binding.specialtiesCheckboxGroup.setSpecialties(viewModel.getSpecialties())
        binding.specialtiesCheckboxGroup.setSpecialtiesViews()


    }

}