package com.example.a212019_nurinfarhana_drrimaniza_project1.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.a212019_nurinfarhana_drrimaniza_project1.EthicViewModel
import com.example.a212019_nurinfarhana_drrimaniza_project1.data.Brand
import com.example.a212019_nurinfarhana_drrimaniza_project1.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BrandListScreen(
    viewModel: EthicViewModel,
    onBrandClick: (Brand) -> Unit
) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Green50)
            .padding(horizontal = 16.dp)
    ) {
        Spacer(Modifier.height(16.dp))
        Text(text = "Search Brands", fontFamily = DmSans, fontWeight = FontWeight.Bold, fontSize = 22.sp, color = Green800)
        Text(text = "Find out how ethical a brand is.", fontFamily = DmSans, fontSize = 13.sp, color = Color.Gray)
        Spacer(Modifier.height(14.dp))

        OutlinedTextField(
            value = uiState.brandSearchQuery,
            onValueChange = { viewModel.updateBrandSearch(it) },
            placeholder = { Text("e.g. Uniqlo, Nike, Zara…", fontFamily = DmSans) },
            leadingIcon = { Icon(Icons.Default.Search, null, tint = Green800) },
            trailingIcon = {
                if (uiState.brandSearchQuery.isNotEmpty()) {
                    IconButton(onClick = { viewModel.updateBrandSearch("") }) {
                        Icon(Icons.Default.Close, null, tint = Color.Gray)
                    }
                }
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),
            textStyle = TextStyle(fontFamily = DmSans, color = TextPrimary),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Green800,
                unfocusedBorderColor = Color.LightGray,
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White
            ),
            shape = RoundedCornerShape(12.dp)
        )
        Spacer(Modifier.height(12.dp))

        val results = uiState.filteredBrands

        if (uiState.brandSearchQuery.isNotBlank()) {
            Text(text = "${results.size} result${if (results.size != 1) "s" else ""} found", fontFamily = DmSans, fontSize = 12.sp, color = Color.Gray)
            Spacer(Modifier.height(8.dp))
        }

        if (uiState.brandSearchQuery.isNotBlank() && results.isEmpty()) {
            Spacer(Modifier.height(60.dp))
            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Text("😕", fontSize = 40.sp)
                Spacer(Modifier.height(8.dp))
                Text(text = "No brands found for \"${uiState.brandSearchQuery}\"", fontFamily = DmSans, color = Color.Gray, textAlign = TextAlign.Center)
            }
        } else {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items(results, key = { it.id }) { brand ->
                    BrandCard(
                        brand = brand,
                        onToggleFav = { viewModel.toggleFavorite(brand.id) },
                        onClick = {
                            viewModel.selectBrand(brand)
                            onBrandClick(brand)
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BrandCard(brand: Brand, onToggleFav: () -> Unit, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(14.dp)
    ) {
        Row(modifier = Modifier.padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = brand.logoRes),
                contentDescription = brand.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(56.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
            Spacer(Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(text = brand.name, fontFamily = DmSans, fontWeight = FontWeight.Bold, fontSize = 15.sp, color = TextPrimary)
                Spacer(Modifier.height(4.dp))
                RatingChip(rating = brand.rating)
                Spacer(Modifier.height(4.dp))
                Text(text = brand.description, fontFamily = DmSans, fontSize = 12.sp, color = Color.Gray, maxLines = 1)
            }
            IconButton(onClick = onToggleFav) {
                Icon(
                    imageVector = if (brand.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Favorite",
                    tint = if (brand.isFavorite) Color(0xFFE53935) else Color.LightGray
                )
            }
        }
    }
}

@Composable
fun RatingChip(rating: String) {
    val bg = when (rating) {
        "Great" -> RatingGreat
        "It's a Start" -> RatingStart
        else -> RatingBad
    }
    Surface(color = bg, shape = RoundedCornerShape(50)) {
        Text(
            text = rating,
            color = Color.White,
            fontSize = 11.sp,
            fontFamily = DmSans,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 4.dp)
        )
    }
}