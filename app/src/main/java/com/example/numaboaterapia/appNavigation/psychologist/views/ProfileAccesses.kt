package com.example.numaboaterapia.appNavigation.psychologist.views

import android.app.DatePickerDialog
import android.content.Intent
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
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class ProfileAccesses : AppCompatActivity() {
    private lateinit var binding: ActivityProfileAccessesBinding
    private lateinit var selectedDate: String
    private lateinit var viewModel: ProfileAccessesViewModel
    private lateinit var usersData: ArrayList<HashMap<String, String>>
    private lateinit var averageData: ArrayList<HashMap<String, String>>
    private var adapter = ProfileAccessAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileAccessesBinding.inflate(layoutInflater)
        viewModel = ProfileAccessesViewModel()
        setUpViews()
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
        binding.profileAccessRecyclerView.clipToPadding = false
        binding.profileAccessRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.profileAccessRecyclerView.adapter = adapter
        setUpRecyclerView()

    }

    private fun setUpRecyclerView() {
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
                adapter.notifyDataSetChanged()
                adapter.setOnItemClickListener(object : ProfileAccessAdapter.onItemClickListener {
                    override fun onItemClick(position: Int) {
                        val intent =
                            Intent(applicationContext, AddPatientToListActivity::class.java)
                        intent.putExtra("name", usersData[position]["name"])
                        intent.putExtra("email", usersData[position]["email"])
                        intent.putExtra("uId", usersData[position]["Uid"])

                        startActivity(intent)
                    }
                })
            }
        }


        viewModel.filteredUsersData.observe(this) { filteredUsersData ->
            adapter.usersData = filteredUsersData
            adapter.notifyDataSetChanged()
            binding.progressAccess.visibility = View.GONE
        }

        viewModel.filteredAverageData.observe(this) { filteredAverageData ->
            adapter.averageData = filteredAverageData
            adapter.notifyDataSetChanged()
            binding.progressAccess.visibility = View.GONE
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
                val selectedCalendar = Calendar.getInstance()
                selectedCalendar.set(selectedYear, selectedMonth, selectedDay)

                val dateFormat = SimpleDateFormat("dd/MMMM/yyyy", Locale.getDefault())
                selectedDate = dateFormat.format(selectedCalendar.time)
                binding.progressAccess.visibility = View.VISIBLE
                viewModel.getFilteredData(selectedDate)
            },
            year,
            month,
            day
        )

        datePickerDialog.show()
    }


}