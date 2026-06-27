package com.example.a212019_nurinfarhana_drrimaniza_project1.screens

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.a212019_nurinfarhana_drrimaniza_project1.EthicViewModel
import com.example.a212019_nurinfarhana_drrimaniza_project1.ui.theme.White

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BrandDetailScreen(navController: NavHostController, viewModel: EthicViewModel) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val brand = state.selectedBrand ?: return

    val ratingColor = ratingColor(brand.rating)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(brand.name) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { viewModel.toggleFavorite(brand.id) }) {
                        Icon(
                            if (brand.isFavorite) Icons.Default.Favorite else Icons.Outlined.FavoriteBorder,
                            contentDescription = "Favourite",
                            tint = if (brand.isFavorite) Color(0xFFE63946)
                            else MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
        ) {
            // Brand logo
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(brand.logoRes),
                    contentDescription = brand.name,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(24.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(brand.name, style = MaterialTheme.typography.headlineSmall)
            Spacer(modifier = Modifier.height(6.dp))
            RatingBadge(rating = brand.rating, color = ratingColor)

            Spacer(modifier = Modifier.height(16.dp))

            // About card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape  = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                elevation = CardDefaults.cardElevation(1.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("About this Brand",
                        style = MaterialTheme.typography.titleSmall,
                        color = MaterialTheme.colorScheme.primary)
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(brand.description, style = MaterialTheme.typography.bodyMedium)
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Rating explanation card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape  = RoundedCornerShape(14.dp),
                colors = CardDefaults.cardColors(
                    containerColor = ratingColor.copy(alpha = 0.10f)
                ),
                elevation = CardDefaults.cardElevation(0.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        "What does \"${brand.rating}\" mean?",
                        style = MaterialTheme.typography.titleSmall,
                        color = ratingColor
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(brand.ratingExplanation, style = MaterialTheme.typography.bodyMedium)
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Disclaimer
            Text(
                "⚠ Ratings are for educational purposes only, compiled from publicly available sustainability reports for academic demonstration.",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}