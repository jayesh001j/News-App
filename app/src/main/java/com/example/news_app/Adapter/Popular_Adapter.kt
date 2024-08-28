package com.example.news_app.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.news_app.R
import com.example.news_app.databinding.PopularTileBinding
import com.example.retrofitapicalling.Moden.ArticlesItem

class Popular_Adapter(val newsModel: List<ArticlesItem?>) : RecyclerView.Adapter<Popular_Adapter.PopularViewHolder>() {

    class PopularViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var binding = PopularTileBinding.bind(itemView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.popular_tile, parent, false)
        return PopularViewHolder(view)
    }

    override fun getItemCount(): Int {
        return newsModel.size
    }

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) {

//        holder.binding.newsImage.setText("${newsModel.get(position)?.title}")
        holder.binding.Headline.setText("${newsModel.get(position)?.title}")
        holder.binding.Description.setText("${newsModel.get(position)?.description}")
//        holder.binding.urlnews.setText("${newsModel.get(position)?.url}")
//        holder.binding.authornews.setText("${newsModel.get(position)?.author}")
//        holder.binding.datenews.setText("${newsModel.get(position)?.publishedAt}")
        Glide.with(holder.itemView).load(newsModel.get(position)?.urlToImage).into(holder.binding.newsImage)

    }
}
