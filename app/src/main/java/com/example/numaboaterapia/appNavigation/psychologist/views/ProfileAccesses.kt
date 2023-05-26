package com.example.numaboaterapia.appNavigation.psychologist.views

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.numaboaterapia.R
import com.example.numaboaterapia.appNavigation.psychologist.viewmodel.ProfileAccessesViewModel
import com.example.numaboaterapia.appNavigation.psychologist.views.adapter.ProfileAccessAdapter
import com.example.numaboaterapia.databinding.ActivityProfileAccessesBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Calendar

class ProfileAccesses : AppCompatActivity() {
    private lateinit var binding: ActivityProfileAccessesBinding
    private lateinit var selectedDate : String
    private lateinit var viewModel: ProfileAccessesViewModel
    private lateinit var usersData : ArrayList<HashMap<String, String>>
    private lateinit var averageData : ArrayList<HashMap<String, String>>
    private var adapter = ProfileAccessAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileAccessesBinding.inflate(layoutInflater)
        viewModel = ProfileAccessesViewModel()
        setUpViews()
        setUpRecyclerView()
        setContentView(binding.root)
    }

    private fun setUpViews() {
        binding.psiAccessToolBar.getBackButton().setOnClickListener {
            finish()
        }

        binding.psiAccessToolBar.setTitleText(R.string.access_label)
        binding.accessFilterButton.setText(R.string.date_button_label)
        binding.accessFilterButton.setOnClickListener {
            showDatePicker()
        }
        binding.profileAccessRecyclerView.adapter = adapter
        binding.profileAccessRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun setUpRecyclerView(){
        binding.accessFilterButton.visibility = View.GONE
        binding.progressAccess.visibility = View.VISIBLE
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.getAccessUsers()
            viewModel.getUsersData()
            viewModel.getUsersAverageIncome()
            withContext(Dispatchers.Main) {
                usersData = viewModel.getUsersInformation()
                averageData = viewModel.getUsersAverageIncomeData()

                binding.accessFilterButton.visibility = View.VISIBLE
                binding.progressAccess.visibility = View.GONE

                adapter.usersData = usersData
                adapter.averageData = averageData
                adapter.setOnClickListener(object : ProfileAccessAdapter.onItemclickListener {
                    override fun onItemClick(position: Int) {

                    }
                })
            }
        }
    }

    private fun showDatePicker() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { _, selectedYear, selectedMonth, selectedDay ->
                // Aqui você pode lidar com a data selecionada
                selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                // Faça o que precisar com a data selecionada
                Toast.makeText(this,
                    "Data selecionada: $selectedDate", Toast.LENGTH_SHORT).show()
            },
            year,
            month,
            day
        )

        datePickerDialog.show()
    }
}