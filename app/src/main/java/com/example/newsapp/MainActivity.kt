package com.example.newsapp

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Response


class MainActivity : AppCompatActivity(), NewsItemClicked {

    private lateinit var rvtodo: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fetchData()
        initViews()
    }

    private fun initViews() {
        rvtodo = findViewById(R.id.rvtodo)
    }

    private fun fetchData() {
        val news = NewsService.newsInstance.getData("in", 1)
        news.enqueue(object : retrofit2.Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                val news = response.body()
                val image = response.body()?.articles?.get(0)?.urlToImage
                if (news != null) {
                    Log.d("Run", "Successful $image")
                    rvtodo.layoutManager = LinearLayoutManager(applicationContext)
                    val mAdapter = NewsListAdapter(response.body()?.articles as ArrayList<Data>)
                    rvtodo.adapter = mAdapter
                    mAdapter.notifyDataSetChanged()
                }
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("News", "Error", t)
            }
        })
    }


    override fun onItemClicked(item: Data) {
        val builder=CustomTabsIntent.Builder()
        val customTabsIntent=builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(item.url))


    }
}