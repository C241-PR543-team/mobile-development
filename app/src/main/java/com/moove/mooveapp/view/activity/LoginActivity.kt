package com.moove.mooveapp.view.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.moove.mooveapp.ViewModelFactory
import com.moove.mooveapp.data.request.LoginRequest
import com.moove.mooveapp.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        viewModel = ViewModelProvider(this, factory).get(LoginViewModel::class.java)

        loginAction()
    }

    private fun loginAction(){
        viewModel.loginResponse.observe(this, Observer { response ->
            Log.d("LoginActivity", "response: $response")
            when (response?.status) {
                "success" -> {
                    AlertDialog.Builder(this).apply {
                        setTitle("Yey!")
                        setMessage("Login berhasil.")
                        setPositiveButton("OK") { _, _ -> }
                        create()
                        show()
                    }
                }
                "fail" -> {
                    AlertDialog.Builder(this).apply {
                        setTitle("Terjadi suatu kesalahan.")
                        setMessage("Email atau password yang kamu masukkan salah.")
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
                    // Handle any other status value
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

        binding.loginButton.setOnClickListener {
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            viewModel.userLogin(LoginRequest(email, password))
        }
    }
}