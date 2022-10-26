package com.example.newsapp

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsListAdapter(private val items: ArrayList<Data>):RecyclerView.Adapter<NewsViewHolder>() {

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
         val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
         return NewsViewHolder(view)

     }
    override fun getItemCount(): Int {
        return items.size
    }

     override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
         val currentItem = items[position]
         holder.title.text=currentItem.title
         holder.author.text=currentItem.author
         Glide.with(holder.itemView.context).load(currentItem.urlToImage).into(holder.images)
         holder.itemView.apply {
             holder.cardView.setOnClickListener {
                 val intent = Intent(context,WebViewActivity::class.java)
                 intent.putExtra("url",items[position].url)
                 context.startActivity(intent)
             }
         }
     }
 }

    class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.title)
        val images:ImageView=itemView.findViewById(R.id.image)
        val author:TextView=itemView.findViewById(R.id.author)
        val cardView:ConstraintLayout=itemView.findViewById(R.id.constraintLayout)
    }
interface NewsItemClicked{
    fun onItemClicked(item: Data)
}


