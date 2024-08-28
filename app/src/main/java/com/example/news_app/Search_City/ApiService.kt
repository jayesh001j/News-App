package com.example.citysearch

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("cities")
    fun getCities(@Query("search") query: String): Call<List<City>>
}
