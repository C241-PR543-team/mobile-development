package com.moove.mooveapp.view.createPlan

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.datepicker.MaterialDatePicker
import com.moove.mooveapp.databinding.ActivityCreateBinding
import java.text.SimpleDateFormat
import java.util.Locale

class CreateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCreateBinding
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val judulTrip = intent.getStringExtra("judul_trip")
        binding.tvJudulTrip.text = judulTrip

        binding.tvDate.setOnClickListener {
            showDateRangePicker()
        }
        binding.btnSavePlan.setOnClickListener {
            createTripPlan()
        }
    }

    private fun showDateRangePicker() {
        val dateRangePicker =
            MaterialDatePicker.Builder.dateRangePicker()
                .setTitleText("Select Trip Dates")
                .build()

        dateRangePicker.show(supportFragmentManager, "date_range_picker")

        dateRangePicker.addOnPositiveButtonClickListener { selection ->
            val startDate = dateFormat.format(selection.first)
            val endDate = dateFormat.format(selection.second)
            binding.tvDate.text = "$startDate - $endDate"
        }
    }

    private fun createTripPlan() {
        val destination = binding.tvJudulTrip.text.toString()
        val dateRange = binding.tvDate.text.toString()

        // Lakukan validasi input
        if (destination.isEmpty() || dateRange.isEmpty()) {
            Toast.makeText(this, "Harap isi semua field", Toast.LENGTH_SHORT).show()
            return
        }

        // Tampilkan pesan sukses
        Toast.makeText(this, "Rencana perjalanan telah dibuat", Toast.LENGTH_SHORT).show()

        // Kembali ke activity sebelumnya atau lakukan aksi lainnya setelah membuat rencana perjalanan
        finish()
    }
}
