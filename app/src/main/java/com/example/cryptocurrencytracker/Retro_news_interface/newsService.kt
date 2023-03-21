package com.example.cryptocurrencytracker.Retro_news_interface

import com.example.cryptocurrencytracker.Model.main_News_call
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


const val BASE_URL="https://newsapi.org/"
const val API_KEY="6566c770e39349c1b7f924d0bc85a62f"
const val NEWS_API="v2/top-headlines?country=in&apiKey=$API_KEY"
const val NEWS_API2="v2/everything?q=cryptocurrency?&apiKey=$API_KEY"
const val NEWS_API3_INDIA="v2/everything?q=India&from=2022-03-17&sortBy=publishedAt?&apiKey=$API_KEY"
const val NEWS_API4_Usa="v2/everything?q=USA&from=2022-03-17&sortBy=publishedAt?&apiKey=$API_KEY"
const val NEWS_API5_business="v2/everything?q=business&from=2022-03-17&sortBy=publishedAt?&apiKey=$API_KEY"
const val NEWS_API6_fashion="v2/everything?q=fashion&from=2022-03-17&sortBy=publishedAt?&apiKey=$API_KEY"
const val NEWS_API7_technology="v2/everything?q=technology&from=2022-03-17&sortBy=publishedAt?&apiKey=$API_KEY"
const val NEWS_API7_Football="v2/everything?q=football&from=2022-03-17&sortBy=publishedAt?&apiKey=$API_KEY"

interface newsService {

    @GET(NEWS_API)
    fun getheadline():Call<main_News_call>

    @GET(NEWS_API2)
    fun getCoins():Call<main_News_call>

    @GET(NEWS_API3_INDIA)
    fun getAll_news_India():Call<main_News_call>


    @GET(NEWS_API4_Usa)
    fun getAll_news_Usa():Call<main_News_call>

    @GET(NEWS_API5_business)
    fun getAll_news_Elon():Call<main_News_call>

    @GET(NEWS_API6_fashion)
    fun getAll_news_Tesla():Call<main_News_call>

    @GET(NEWS_API7_technology)
    fun getAll_news_Technology():Call<main_News_call>

    @GET(NEWS_API7_Football)
    fun getAll_news_Football():Call<main_News_call>

}

object Newservice{
    val newsinstance:newsService

    init {

        val retrofit=Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        newsinstance=retrofit.create(newsService::class.java)
    }
}
