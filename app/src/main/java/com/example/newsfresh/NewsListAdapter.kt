package com.example.newsfresh

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

//adapter ko data chahiye hota hai uske constructor ke andar aur wo data activity laake de rahi hoti hai
//adapter ke andar ham btate hai ki ye kiss type ka view holder hai
class NewsListAdapter(private val listener:NewsItemClicked): RecyclerView.Adapter<NewsViewHolder>() {
    private val items:ArrayList<News> =ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {//ye tab call hota hai jab ham view holder ko create kar rahe hote hai aur ye method naye view holder ko return kar raha hota hai


        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_news,parent,false)
        val viewHolder=NewsViewHolder(view)
        view.setOnClickListener{
            listener.onItemClicked(items[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun getItemCount(): Int {//sirf first time call hota hai aur btata hai ki kitne items isme rehne wale hai
        return items.size
    }
//                                                  konse item ko bind krna hai
    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
    val currentItem=items[position]
    holder.titleView.text=currentItem.title
    holder.author.text=currentItem.author
    Glide.with(holder.itemView.context).load(currentItem.imageUrl).into(holder.image)
}
    fun updateNews(updatedNews:ArrayList<News>)
    {
        items.clear()
        items.addAll(updatedNews)
        notifyDataSetChanged()
    }
}
class NewsViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView)//View holder ki class
{
val titleView: TextView =itemView.findViewById(R.id.title)
    val image: ImageView =itemView.findViewById(R.id.image)
    val author:TextView=itemView.findViewById(R.id.author)
}
interface NewsItemClicked{
    fun onItemClicked(item:News)
}