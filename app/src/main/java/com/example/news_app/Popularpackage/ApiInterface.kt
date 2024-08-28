package com.example.retrofitapicalling
import retrofit2.Call
import com.example.retrofitapicalling.Moden.NewsModel
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiInterface {



        @GET("top-headlines")

        fun getNews(@Query("country") country: String,
                    @Query("category") category: String,
                    @Query("apiKey") apiKey: String = "c3c66065028b4035905006bbbcfa1c24"): Call<NewsModel>

    }

//?country=us&category=business&apiKey=c3c66065028b4035905006bbbcfa1c24



/*


*POJO -> POKO
* POJO -> Plain Old Java Object
* POJO -> Plain Old Kotlin Object


 */