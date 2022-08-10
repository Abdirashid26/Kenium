package com.example.kenium.adapters

import android.animation.LayoutTransition
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kenium.ArticleActivity
import com.example.kenium.R
import com.example.kenium.model.Article
import com.google.android.material.chip.Chip
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.internal.artificialFrame

class ArticleAdapter (val context: Context, val artcileList : MutableList<Article>) : RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {

    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        var image = itemView.findViewById<ImageView>(R.id.image)
        var heading  = itemView.findViewById<TextView>(R.id.heading)
        var likes = itemView.findViewById<Chip>(R.id.likes)
        var author = itemView.findViewById<Chip>(R.id.author)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater= LayoutInflater.from(context).inflate(R.layout.article_item,parent,false)
        return ViewHolder(layoutInflater)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Glide.with(context).load(artcileList.get(position).image).into(holder.image)
        holder.heading.text = artcileList.get(position).headline
        holder.likes.text = "Likes :" + artcileList.get(position).likes
        holder.author.text = artcileList.get(position).author


        holder.itemView.setOnClickListener {
            Toast.makeText(context,"Pressed !",Toast.LENGTH_SHORT).show()
            val intent = Intent(holder.itemView.context,ArticleActivity::class.java)

            context.startActivity(intent)



        }

    }

    override fun getItemCount(): Int {
        return  artcileList.size
    }


}