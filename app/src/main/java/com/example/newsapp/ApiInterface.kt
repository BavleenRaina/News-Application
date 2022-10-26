package com.example.newsapp

import android.telecom.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

const val BASE_URL= "https://newsapi.org/v2/"
const val API_KEY="5f415d79890d40b1819fffa254df522d"

interface ApiInterface {
    @GET("top-headlines?apiKey=$API_KEY")
    fun getData(@Query("country")country:String,
                @Query("page")page:Int):retrofit2.Call<News>

}
object NewsService{
    val newsInstance:ApiInterface
    init {
        val retrofit= Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        newsInstance=retrofit.create(ApiInterface::class.java)
    }
}