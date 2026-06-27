package com.example.a212019_nurinfarhana_drrimaniza_project1.data

import androidx.annotation.DrawableRes

data class ClothingItem(
    val id: Int,
    val name: String,
    val brand: String,
    val category: String,
    val originalPrice: Double,
    val salePrice: Double,
    @DrawableRes val imageRes: Int,
    val description: String
)