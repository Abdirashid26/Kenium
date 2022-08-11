package com.example.kenium.model

import java.io.Serializable


data class Article(
    var headline : String = "",
    var summary: String = "",
    var image: String = "",
    var likes: Int = 0,
    var file : String = "",
    var author : String =""
) : Serializable

