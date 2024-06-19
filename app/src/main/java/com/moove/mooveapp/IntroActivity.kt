package com.moove.mooveapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.moove.mooveapp.databinding.ActivityIntroBinding

class IntroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewPager()
        setupButtons()
    }

    private fun setupViewPager() {
        val adapter = ViewPagerAdapter(this)
        binding.slideViewPager.adapter = adapter

        binding.slideViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
            }
        })
    }

    private fun setupButtons() {
        binding.btnSkip.setOnClickListener {
            navigateToHome()
        }

        binding.btnNext.setOnClickListener {
            val currentItem = binding.slideViewPager.currentItem
            if (currentItem + 1 < binding.slideViewPager.adapter!!.itemCount) {
                binding.slideViewPager.currentItem = currentItem + 1
            } else {
                navigateToHome()
            }
        }

        binding.btnBack.setOnClickListener {
            val currentItem = binding.slideViewPager.currentItem
            if (currentItem - 1 >= 0) {
                binding.slideViewPager.currentItem = currentItem - 1
            }
        }
    }

    private fun navigateToHome() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
