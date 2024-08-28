package com.example.news_app

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.viewpager.widget.ViewPager
import com.example.news_app.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import layout.NewsAdapter

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        val tablayout = findViewById<TabLayout>(R.id.tabLayout)
        val viewpager = findViewById<ViewPager>(R.id.viewPager)
        var adapter = NewsAdapter(supportFragmentManager)

        viewpager.adapter = adapter
        tablayout.setupWithViewPager(viewpager)

        //method 2
        tablayout.getTabAt(0)?.text = "Popular"
        tablayout.getTabAt(1)?.text = "All"
        tablayout.getTabAt(2)?.text = "Health"
        tablayout.getTabAt(3)?.text = "Science"

    }
}