package com.moove.mooveapp.view.activity

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.moove.mooveapp.ViewModelFactory
import com.moove.mooveapp.data.request.RegisterRequest
import com.moove.mooveapp.databinding.ActivitySignupBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private lateinit var viewModel: SignupViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory).get(SignupViewModel::class.java)

        signupAction()

        binding.tvForgotPassword.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.etDate.setOnClickListener {
            showDatePickerDialog()
        }
    }

    private fun signupAction() {
        viewModel.registerResponse.observe(this, Observer { response ->
            Log.d("SignupActivity", "response: '${response?.status}'")
            when (response?.status) {
                "success" -> {
                    AlertDialog.Builder(this).apply {
                        setTitle("Yey!")
                        setMessage("Registrasi berhasil.")
                        setPositiveButton("OK") { _, _ -> }
                        create()
                        show()
                    }
                }
                "fail" -> {
                    AlertDialog.Builder(this).apply {
                        setTitle("Terjadi suatu kesalahan.")
                        setMessage("Registrasi gagal.")
                        setPositiveButton("OK") { _, _ -> }
                        create()
                        show()
                    }
                }
                "error" -> {
                    AlertDialog.Builder(this).apply {
                        setTitle("Terjadi suatu kesalahan.")
                        setMessage("Tidak dapat terkoneksi ke server.")
                        setPositiveButton("OK") { _, _ -> }
                        create()
                        show()
                    }
                }
                else -> {
                    AlertDialog.Builder(this).apply {
                        setTitle("Oops")
                        setMessage("An unknown status value was received: ${response?.status}")
                        setPositiveButton("OK") { _, _ -> }
                        create()
                        show()
                    }
                }
            }
        })

        binding.signUpButton.setOnClickListener{
            val name = binding.etName.text.toString()
            val phone = binding.etPhone.text.toString()
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            val birthday = binding.etDate.text.toString()
            viewModel.userRegister(RegisterRequest(name, phone, birthday, email, password))
        }
    }

    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this,
            { _, selectedYear, selectedMonth, selectedDay ->
                // Set the selected date to the EditText in yyyy-MM-dd format
                val selectedDate = Calendar.getInstance()
                selectedDate.set(selectedYear, selectedMonth, selectedDay)
                val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
                val formattedDate = dateFormat.format(selectedDate.time)
                binding.etDate.setText(formattedDate)
            },
            year,
            month,
            day
        )

        // Show the date picker dialog
        datePickerDialog.show()
    }
}