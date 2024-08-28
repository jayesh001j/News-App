package com.example.news_app.Search_City

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.citysearch.ApiService
import com.example.citysearch.City
import com.example.citysearch.CityAdapter
import com.example.news_app.MainActivity
import com.example.news_app.R
import com.example.news_app.Sing_inActivity
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SearchActivity : AppCompatActivity() {
    private lateinit var cityAdapter: CityAdapter
    private lateinit var cityList: RecyclerView
    private val cities = mutableListOf<City>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_search)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        cityList = findViewById(R.id.city_list)
        cityList.layoutManager = LinearLayoutManager(this)
        cityAdapter = CityAdapter(cities)
        cityList.adapter = cityAdapter

        val searchCityEditText = findViewById<EditText>(R.id.search_city)
        searchCityEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val query = s.toString()
                fetchCities(query)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        findViewById<Button>(R.id.next_button).setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            // Handle Next button click
        }
    }

    private fun fetchCities(query: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.example.com/") // Replace with your base URL
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().build())
            .build()

        val apiService = retrofit.create(ApiService::class.java)
        apiService.getCities(query).enqueue(object : Callback<List<City>> {
            override fun onResponse(call: Call<List<City>>, response: Response<List<City>>) {
                if (response.isSuccessful) {
                    cities.clear()
                    cities.addAll(response.body()!!)
                    cityAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<List<City>>, t: Throwable) {
                // Handle failure
            }
        })
    }
}
