package com.example.cryptocurrencytracker.Adapter

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cryptocurrencytracker.Model.news_Article_Model
import com.example.cryptocurrencytracker.R


class newsAdapter(val context: Context, val articles_list: List<news_Article_Model>) :
    RecyclerView.Adapter<newsAdapter.myNewsviewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myNewsviewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return myNewsviewHolder(view)
    }

    override fun onBindViewHolder(holder: myNewsviewHolder, position: Int) {

        val articleModel = articles_list[position]
        holder.txttile.text = articleModel.title
        holder.txtdes.text = articleModel.description
        var urlstring =articleModel.url
        Glide.with(context).load(articleModel.urlToImage).into(holder.newsImage)

            holder.btn.setOnClickListener {

                val uri: Uri = Uri.parse(articleModel.url)
                val intent = Intent(Intent.ACTION_VIEW,uri)
                context.startActivity(intent)

            }

        }

    override fun getItemCount(): Int {
        return articles_list.size
    }

    class myNewsviewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var txttile = itemView.findViewById<TextView>(R.id.textview)
        var txtdes = itemView.findViewById<TextView>(R.id.textView2)
        var newsImage = itemView.findViewById<ImageView>(R.id.newsImage)
        var item_cointainer = itemView.findViewById<RelativeLayout>(R.id.item_container)
        var btn=itemView.findViewById<Button>(R.id.btnurl)


    }
}