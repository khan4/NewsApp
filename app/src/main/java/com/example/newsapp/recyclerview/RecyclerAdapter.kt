package com.example.newsapp.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.newspojo.Articles

class RecyclerAdapter() : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    lateinit var context: Context
    lateinit var newsList: List<Articles>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.recycler_item_view,parent,false);
        return NewsViewHolder(view)
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when(holder){
            is NewsViewHolder ->{
                var articles = newsList.get(position)
                holder.tvAuthor.setText(articles.author)
                holder.tvDescription.setText(articles.description)
                Glide.with(context).load(articles.urlToImage).into(holder.imageView)
            }
        }
    }

    fun initNewsList(newsList : List<Articles>){
        this.newsList = newsList
    }

    class NewsViewHolder : RecyclerView.ViewHolder {

         var imageView:ImageView
         var tvDescription:TextView
         var tvAuthor:TextView
        constructor(view: View) :super(view){
            imageView = view.findViewById(R.id.imageView)
            tvDescription = view.findViewById(R.id.tvDescription)
            tvAuthor = view.findViewById(R.id.tvAuthor)
        }

    }
}


