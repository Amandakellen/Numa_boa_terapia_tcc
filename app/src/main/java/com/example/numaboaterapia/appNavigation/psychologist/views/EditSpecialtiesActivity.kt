package com.example.numaboaterapia.appNavigation.psychologist.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import com.example.numaboaterapia.R
import com.example.numaboaterapia.appNavigation.psychologist.viewmodel.MyProfilePsiViewModel
import com.example.numaboaterapia.databinding.ActivityEditSpecialtiesBinding

class EditSpecialtiesActivity : AppCompatActivity() {
    private lateinit var myActivityResultLauncher: ActivityResultLauncher<Intent>
    private lateinit var binding: ActivityEditSpecialtiesBinding
    private lateinit var viewModel : MyProfilePsiViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditSpecialtiesBinding.inflate(layoutInflater)
        viewModel = MyProfilePsiViewModel()
        setUpViews()
        setUpSearch()
        setUpCheckBoxes()
        setContentView(binding.root)
    }

    private fun setUpViews(){
        binding.editSpecialtiesPsiToolBar.setTitleText(R.string.edit_specialties)
        binding.editSpecialtiesPsiToolBar.getBackButton().setOnClickListener {
            finish()
        }

        binding.editSpecialtiesSaveButton.setText(R.string.save)
        binding.editSpecialtiesCancelButton.setOnClickListener {
            finish()
        }


    }

    private fun setUpSearch(){
        val itens = viewModel.getSpecialties()

        binding.editSpecialitiesTextInputEdit.doOnTextChanged { text, start, before, count ->
            viewModel.setSearch(text.toString())
        }

        binding.editSpecialitiesTextInputEdit.doAfterTextChanged {
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
                    binding.editSpecialtiesCheckboxGroup.changeVisibilitySearch(itensSearch)


                }
            } else {
                binding.editSpecialtiesCheckboxGroup.visibilityAll()
            }

            viewModel.setItensSelected(binding.editSpecialtiesCheckboxGroup.specialtiesChecked)

        }
    }

    private fun setUpCheckBoxes() {
        binding.editSpecialtiesProgressBar.visibility = View.VISIBLE
        binding.editSpecialtiesScrollview.visibility = View.GONE

        val result = viewModel.getSpecialtiesCollection()
        result.invokeOnCompletion {
            binding.editSpecialtiesScrollview.visibility = View.VISIBLE
            binding.editSpecialtiesProgressBar.visibility = View.GONE

            val specialties = viewModel.getSpecialtiesData()

            specialties?.let { it1 ->
                binding.editSpecialtiesCheckboxGroup.setSpecialtiesFirebase(
                    it1
                )
            }

            binding.editSpecialtiesCheckboxGroup.setSpecialties(viewModel.getSpecialties())
            binding.editSpecialtiesCheckboxGroup.setSpecialtiesViews()

            binding.editSpecialtiesSaveButton.setOnClickListener {
                if(binding.editSpecialtiesCheckboxGroup.isCheckedEmpty()){
                    Toast.makeText(
                        this,
                        "Selecione ao menos 1 item!",
                        Toast.LENGTH_SHORT
                    ).show()
                }else{
                    viewModel.setItensSelected(binding.editSpecialtiesCheckboxGroup.ischecked())
                    val updateResult = viewModel.UpdateSpecialtiesCollection()
                    updateResult.invokeOnCompletion {
                        val intent = Intent(this, MyProfilePsiActivity::class.java)
                        startActivity(intent)
                        //myActivityResultLauncher.launch(intent)
                    }
                }
            }

        }

    }
}