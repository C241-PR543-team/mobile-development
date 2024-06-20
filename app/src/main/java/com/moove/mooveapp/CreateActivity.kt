import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
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
        binding.tvJudulTrip.text = judulTrip

        binding.tvStartDate.setOnClickListener {
            showDatePickerDialog(binding.tvStartDate)
        }

        binding.tvEndDate.setOnClickListener {
            showDatePickerDialog(binding.tvEndDate)
        }

        binding.btnSavePlan.setOnClickListener {
            createTripPlan()
        }
    }

    private fun showDatePickerDialog(editText: TextView) {
        val calendar = Calendar.getInstance()
        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, month, dayOfMonth ->
                val selectedDate = Calendar.getInstance()
                selectedDate.set(year, month, dayOfMonth)
                editText.setText(dateFormat.format(selectedDate.time))
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }

    private fun createTripPlan() {
        val destination = binding.tvJudulTrip.text.toString()
        val startDate = binding.tvStartDate.text.toString()
        val endDate = binding.tvEndDate.text.toString()

        // Lakukan validasi input
        if (destination.isEmpty() || startDate.isEmpty() || endDate.isEmpty()) {
            Toast.makeText(this, "Harap isi semua field", Toast.LENGTH_SHORT).show()
            return
        }

        // Simpan data rencana perjalanan atau kirim ke server
        // Contoh: Menyimpan ke Shared Preferences
        saveTripPlan(destination, startDate, endDate)

        // Tampilkan pesan sukses
        Toast.makeText(this, "Rencana perjalanan telah dibuat", Toast.LENGTH_SHORT).show()

        // Kembali ke activity sebelumnya atau lakukan aksi lainnya setelah membuat rencana perjalanan
        finish()
    }

    private fun saveTripPlan(destination: String, startDate: String, endDate: String) {
        // Simpan data ke Shared Preferences atau lakukan aksi penyimpanan lainnya
        // Contoh: Menggunakan Shared Preferences
        val sharedPreferences = getSharedPreferences("TripPlans", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("destination", destination)
        editor.putString("startDate", startDate)
        editor.putString("endDate", endDate)
        editor.apply()
    }
}
