package com.moove.mooveapp

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.moove.mooveapp.databinding.ActivityProfileBinding
import com.moove.mooveapp.view.userActivity.LoginActivity

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnlogout.setOnClickListener {
            logout()
        }
    }

    private fun logout() {
        // Clear SharedPreferences
        val preferences: SharedPreferences = getSharedPreferences("user_session", MODE_PRIVATE)
        val editor = preferences.edit()
        editor.clear()
        editor.apply()

        // Redirect to Login Activity
        val intent = Intent(this@ProfileActivity, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }
}
