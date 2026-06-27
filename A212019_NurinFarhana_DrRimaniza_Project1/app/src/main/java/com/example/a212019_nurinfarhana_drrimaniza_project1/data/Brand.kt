package com.example.a212019_nurinfarhana_drrimaniza_project1.data

import androidx.annotation.DrawableRes

data class Brand(
    val id: Int,
    val name: String,
    val rating: String,
    val description: String,
    val ratingExplanation: String,
    val category: String,
    @DrawableRes val logoRes: Int,
    val isFavorite: Boolean = false
)