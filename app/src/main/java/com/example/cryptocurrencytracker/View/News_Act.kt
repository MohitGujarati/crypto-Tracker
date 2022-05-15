package com.example.cryptocurrencytracker.View

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptocurrencytracker.Adapter.newsAdapter
import com.example.cryptocurrencytracker.Model.main_News_call
import com.example.cryptocurrencytracker.R
import com.example.cryptocurrencytracker.Retro_news_interface.Newservice
import retrofit2.Call
import retrofit2.Response

class News_Act : AppCompatActivity() {

    lateinit var adapter: newsAdapter
    lateinit var newsrecview: RecyclerView;


    lateinit var btn_allnews_News: Button;
    lateinit var btn_allnews_Crypto: Button;
    lateinit var btn_allnews_Indian: Button;
    lateinit var btn_allnews_business: Button;
    lateinit var btn_allnews_fashion: Button;
    lateinit var btn_allnews_Usa: Button;
    lateinit var btn_allnews_tech: Button;
    lateinit var btn_football: Button;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)


        getActionBar()?.hide()
        getSupportActionBar()?.hide()

        newsrecview = findViewById(R.id.news_recview)
        btn_allnews_News = findViewById(R.id.btn_allNews)
        btn_allnews_Crypto = findViewById(R.id.btn_btn_allcrypto)
        btn_allnews_Indian = findViewById(R.id.btn_allnews_Indian)
        btn_allnews_business = findViewById(R.id.btn_allnews_business)
        btn_allnews_fashion = findViewById(R.id.btn_allnews_fashion)
        btn_allnews_Usa = findViewById(R.id.btn_allnews_Usa)
        btn_allnews_tech = findViewById(R.id.btn_allnews_tech)
        btn_football = findViewById(R.id.btn_football)

        getallnews()
        btn_allnews_News.setOnClickListener {
            getallnews()
        }

        btn_football.setOnClickListener {
            getallnews_Football()
        }

        btn_allnews_Crypto.setOnClickListener {
            getcryptonews()
        }

        btn_allnews_Indian.setOnClickListener {
            getall_Indian_news()
        }

        btn_allnews_Crypto.setOnClickListener {
            getcryptonews()
        }

        btn_allnews_business.setOnClickListener {
            getallnews_business()
        }

        btn_allnews_fashion.setOnClickListener {
            getallnews_fashion()
        }

        btn_allnews_Usa.setOnClickListener {
            getallnews_Usa()
        }

        btn_allnews_tech.setOnClickListener {
            getallnews_technology()
        }


    }

    private fun getallnews_Football() {
        val getAll_news_football = Newservice.newsinstance.getAll_news_Football()
        getretro_process(getAll_news_football)
    }

    private fun getallnews_technology() {
        val getAll_news_technology = Newservice.newsinstance.getAll_news_Technology()
        getretro_process(getAll_news_technology)
    }

    private fun getallnews_business() {
        val getAll_news_business = Newservice.newsinstance.getAll_news_Elon()
        getretro_process(getAll_news_business)
    }

    private fun getallnews_fashion() {
        val getAll_news_fashion = Newservice.newsinstance.getAll_news_Tesla()
        getretro_process(getAll_news_fashion)
    }

    private fun getallnews_Usa(){
        val getAll_news_Usa = Newservice.newsinstance.getAll_news_Usa()
        getretro_process(getAll_news_Usa)
    }

   private fun getall_Indian_news(){
        val getAll_news_India = Newservice.newsinstance.getAll_news_India()
        getretro_process(getAll_news_India)
    }

    private fun getallnews() {
        val getall_news = Newservice.newsinstance.getheadline()
        getretro_process(getall_news)

    }

    private fun getcryptonews() {
        val getcryptonewssdata = Newservice.newsinstance.getCoins()
        getretro_process(getcryptonewssdata)
    }

    private fun getretro_process(data: Call<main_News_call>) {
        data.enqueue(object : retrofit2.Callback<main_News_call> {
            override fun onResponse(
                call: Call<main_News_call>,
                response: Response<main_News_call>
            ) {
                val coin = response.body()

                if (coin != null) {
                    Log.d("mohitcode", coin.toString())
                    adapter = newsAdapter(this@News_Act, coin.articles)

                    newsrecview.hasFixedSize()
                    newsrecview.adapter = adapter
                    newsrecview.layoutManager = LinearLayoutManager(this@News_Act)

                }
            }

            override fun onFailure(call: Call<main_News_call>, t: Throwable) {
                Log.d("mohitcode", "error in fetching the news")

            }
        })

    }


}