package com.example.a212019_nurinfarhana_drrimaniza_project1.data

import androidx.annotation.DrawableRes

data class NewsItem(
    val id: Int,
    val title: String,
    val timeAgo: String,
    @DrawableRes val imageRes: Int
)