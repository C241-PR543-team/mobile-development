package com.moove.mooveapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.moove.mooveapp.data.UserRepository
import com.moove.mooveapp.data.pref.UserPreference
import com.moove.mooveapp.data.pref.dataStore
import com.moove.mooveapp.data.retrofit.ApiConfig
import com.moove.mooveapp.data.retrofit.ApiService
import com.moove.mooveapp.databinding.ActivityMainBinding
import com.moove.mooveapp.view.adapter.ReviewUserAdapter
import com.moove.mooveapp.view.home.destinasi.DestinasiPopulerAdapter
import com.moove.mooveapp.view.home.destinasi.DestinasiPopulerItem
import com.moove.mooveapp.view.home.review.ReviewUserItem
import com.moove.mooveapp.view.userActivity.LoginActivity
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var destinasiPopulerAdapter: DestinasiPopulerAdapter
    private lateinit var reviewUserAdapter: ReviewUserAdapter
    private lateinit var SearchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        isLoggedIn()

        lifecycleScope.launch {
            val username = getUsernameFromPreferences()
            displayWelcomeMessage(username)
        }

        setupBottomNavigation()
        setupDestinasiPopulerRecyclerView()
        setupReviewsRecyclerView()
    }

    private fun setupDestinasiPopulerRecyclerView() {
        val destinasiPopulerList = generateDummyDestinasiPopuler()

        destinasiPopulerAdapter = DestinasiPopulerAdapter(this, destinasiPopulerList)
        binding.recyclerViewDestinasiPopuler.apply {
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = destinasiPopulerAdapter
        }
    }

    private fun generateDummyDestinasiPopuler(): List<DestinasiPopulerItem> {
        val list = mutableListOf<DestinasiPopulerItem>()
        list.add(DestinasiPopulerItem(R.drawable.img_bali, "Bali"))
        list.add(DestinasiPopulerItem(R.drawable.img_jateng, "Jawa Tengah"))
        list.add(DestinasiPopulerItem(R.drawable.img_lombok, "Lombok"))
        list.add(DestinasiPopulerItem(R.drawable.img_tanatoraja, "Tana Toraja"))
        return list
    }

    private fun setupReviewsRecyclerView() {
        val reviewsList = mutableListOf<ReviewUserItem>()
        reviewsList.add(ReviewUserItem(R.drawable.img_traveler_bali, "User 1", "Review 1"))
        reviewsList.add(ReviewUserItem(R.drawable.img_traveler_borobudur, "User 2", "Review 2"))
        reviewsList.add(ReviewUserItem(R.drawable.img_traveler_danautoba, "User 3", "Review 3"))

        reviewUserAdapter = ReviewUserAdapter(reviewsList)
        binding.recyclerViewReviews.apply {
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = reviewUserAdapter
        }
    }

    private fun isLoggedIn() {
        val apiConfig = ApiConfig()
        val apiService = apiConfig.getApiService(this, "")
        val repository = UserRepository.getInstance(apiService, UserPreference.getInstance(dataStore))

        val user = runBlocking { repository.getSession().first() }

        if (user?.token.isNullOrEmpty()) {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
            return
        }
    }

    private fun getUsernameFromPreferences(): String {
        val apiConfig = ApiConfig()
        val apiService = apiConfig.getApiService(this, "")
        val repository = UserRepository.getInstance(apiService, UserPreference.getInstance(dataStore))

        val user = runBlocking { repository.getSession().first() }

        return user.name ?: "User"
    }

    private fun displayWelcomeMessage(username: String) {
        binding.tvWelcome.text = "Welcome, $username!"
    }

    private fun showCreatePlanBottomSheet() {
        val view = layoutInflater.inflate(R.layout.bottom_sheet_create_trip, null)

        val etJudulTrip = view.findViewById<EditText>(R.id.et_judul_trip)
        val btnBuat = view.findViewById<Button>(R.id.btn_buat)

        val dialog = BottomSheetDialog(this)
        dialog.setContentView(view)

        btnBuat.setOnClickListener {
            val judulTrip = etJudulTrip.text.toString()
            if (judulTrip.isNotEmpty()) {
                val intent = Intent(this, CreateActivity::class.java)
                intent.putExtra("judul_trip", judulTrip)
                startActivity(intent)
                dialog.dismiss()
            } else {
                Toast.makeText(this, "Judul Trip tidak boleh kosong", Toast.LENGTH_SHORT).show()
            }
        }

        dialog.show()
    }

    private fun setupBottomNavigation() {
        binding.bottomNavigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    // Handle home navigation
                    true
                }

                R.id.navigation_profile -> {
                    // Navigate to ProfileActivity
                    Log.d("MainActivity", "Profile clicked")
                    val intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)
                    true
                }

                R.id.navigation_add_plan -> {
                    showCreatePlanBottomSheet() // Tampilkan bottom sheet saat item ini diklik
                    true
                }
                // Handle other navigation items if present
                else -> false
            }
        }
    }
}