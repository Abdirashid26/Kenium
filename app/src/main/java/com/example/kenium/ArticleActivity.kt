package com.example.kenium

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.kenium.model.Article
import com.google.android.material.button.MaterialButton

class ArticleActivity : AppCompatActivity() {

    lateinit var image: ImageView
    lateinit var headline : TextView
    lateinit var summary : TextView
    lateinit var likes: MaterialButton
    lateinit var author:TextView


    @SuppressLint("CheckResult", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article)


        image = findViewById(R.id.image)
        headline = findViewById(R.id.heading)
        summary = findViewById(R.id.summary)
        likes = findViewById(R.id.likes)
        author = findViewById(R.id.author)


        val article : Article = intent.getSerializableExtra("Article") as Article


        Glide.with(this).load(article.image).into(image)
        println(article.image)
        headline.text = article.headline
        summary.text = article.summary
        likes.text = "Likes : " + article.likes
        author.text  =article.author







    }
}