package com.moove.mooveapp

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.moove.mooveapp.databinding.ActivityCreateBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class CreateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateBinding
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val judulTrip = intent.getStringExtra("judul_trip")
        binding.tvJudulTrip.setText(judulTrip)

        binding.tvStartDate.setOnClickListener {
            showDatePickerDialog { date ->
                binding.tvStartDate.setText(date)
            }
        }

        binding.tvEndDate.setOnClickListener {
            showDatePickerDialog { date ->
                binding.tvEndDate.setText(date)
            }
        }

        binding.btnSavePlan.setOnClickListener {
            // Implementasikan logika penyimpanan rencana perjalanan di sini
        }
    }

    private fun showDatePickerDialog(onDateSet: (String) -> Unit) {
        val calendar = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(year, month, dayOfMonth)
                onDateSet(dateFormat.format(selectedDate.time))
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }
}
