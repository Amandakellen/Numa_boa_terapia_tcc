package com.example.numaboaterapia.appNavigation.psychologist.views

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.numaboaterapia.R
import com.example.numaboaterapia.databinding.ActivityProfileAccessesBinding
import java.util.Calendar

class ProfileAccesses : AppCompatActivity() {
    private lateinit var binding: ActivityProfileAccessesBinding
    private lateinit var selectedDate : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileAccessesBinding.inflate(layoutInflater)
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