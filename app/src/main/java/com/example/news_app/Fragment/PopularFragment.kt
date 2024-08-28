package com.example.news_app.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.news_app.Adapter.Popular_Adapter
import com.example.news_app.R
import com.example.news_app.databinding.FragmentPopularBinding
import com.example.retrofitapicalling.APIClient.APIClient
import com.example.retrofitapicalling.ApiInterface
import com.example.retrofitapicalling.Moden.ArticlesItem
import com.example.retrofitapicalling.Moden.NewsModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class PopularFragment : Fragment() {

    private lateinit var binding: FragmentPopularBinding // Use FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPopularBinding.inflate(inflater, container, false) // Inflate the binding object

        newsAPICalling("ch", "business")

        return binding.root
    }

    fun rvdata(newsModel: List<ArticlesItem?>) {
        val adapter = Popular_Adapter(newsModel)
        binding.RvAPIData.adapter = adapter
    }

    fun newsAPICalling(country: String, category: String) {
        val apiInterface = APIClient.getClient()?.create(ApiInterface::class.java)

        apiInterface?.getNews(country, category)?.enqueue(object : Callback<NewsModel> {
            override fun onResponse(call: Call<NewsModel>, response: Response<NewsModel>) {
                val modaClass = response.body()
                Log.i("TAG", "onResponse: ${modaClass!!.articles!!.get(0)!!.title}")

                rvdata(modaClass.articles!!)
            }

            override fun onFailure(call: Call<NewsModel>, t: Throwable) {
                Log.e("Error", "onFailure: ${t.message}")
            }
        })
    }
}