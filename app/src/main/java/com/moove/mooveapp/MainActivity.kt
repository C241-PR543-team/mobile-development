package com.moove.mooveapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.moove.mooveapp.databinding.ActivityMainBinding
import com.moove.mooveapp.view.adapter.ReviewUserAdapter
import com.moove.mooveapp.view.home.destinasi.DestinasiPopulerAdapter
import com.moove.mooveapp.view.home.destinasi.DestinasiPopulerItem
import com.moove.mooveapp.view.home.review.ReviewUserItem

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

//        if (!isLoggedIn()) {
//            // User is not logged in, redirect to LoginActivity
//            val intent = Intent(this, LoginActivity::class.java)
//            startActivity(intent)
//            finish()
//            return
//        }

        val username = getUsernameFromPreferences()
        displayWelcomeMessage(username)

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

//    private fun isLoggedIn(): Boolean {
//        // Cek apakah pengguna sudah login, misalnya dengan mengecek token di SharedPreferences
//        val sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
//        return sharedPreferences.getString("username", null) != null
//    }

    private fun getUsernameFromPreferences(): String {
        val sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
        return sharedPreferences.getString("username", "User") ?: "User"
    }

    private fun displayWelcomeMessage(username: String) {
        binding.tvWelcome.text = "Welcome, $username!"
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
                    val intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)
                    true
                }

                R.id.navigation_create_plan -> {
                    val intent = Intent(this, CreateActivity::class.java)
                    startActivity(intent)
                    true
                }
                // Handle other navigation items if present
                else -> false
            }
        }
    }
}
