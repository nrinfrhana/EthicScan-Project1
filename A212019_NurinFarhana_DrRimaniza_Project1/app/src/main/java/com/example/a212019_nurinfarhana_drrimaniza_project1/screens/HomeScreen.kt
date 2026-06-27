package com.example.a212019_nurinfarhana_drrimaniza_project1.screens

import androidx.compose.animation.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.a212019_nurinfarhana_drrimaniza_project1.EthicViewModel
import com.example.a212019_nurinfarhana_drrimaniza_project1.R
import com.example.a212019_nurinfarhana_drrimaniza_project1.data.Brand
import com.example.a212019_nurinfarhana_drrimaniza_project1.data.NewsItem
import com.example.a212019_nurinfarhana_drrimaniza_project1.data.SampleData
import com.example.a212019_nurinfarhana_drrimaniza_project1.navigation.Screen
import com.example.a212019_nurinfarhana_drrimaniza_project1.ui.theme.*

@Composable
fun HomeScreen(navController: NavHostController, viewModel: EthicViewModel) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // ── Greeting ──────────────────────────────────────────────
        item {
            Column(modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 8.dp)) {
                Text(
                    "Hello 👋",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    "Make ethical choices today.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }

        // ── Hero image ────────────────────────────────────────────
        item {
            Box(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(16.dp))
            ) {
                Image(
                    painter = painterResource(R.drawable.ethicgreen),
                    contentDescription = "Ethic Green Banner",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }

        // ── Expandable Search Card ────────────────────────────────
        item {
            ExpandableSearchCard(
                isExpanded    = state.isSearchExpanded,
                searchQuery   = state.brandSearchQuery,
                onToggle      = { viewModel.toggleSearchExpanded() },
                onQueryChange = { viewModel.updateBrandSearch(it) }
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        // ── Brand list inside expanded card ───────────────────────
        if (state.isSearchExpanded) {
            items(state.filteredBrands, key = { it.id }) { brand ->
                BrandRowItem(
                    brand = brand,
                    onFavoriteToggle = { viewModel.toggleFavorite(brand.id) },
                    onClick = {
                        viewModel.selectBrand(brand)
                        navController.navigate(Screen.BrandDetail.route)
                    }
                )
            }
            item { Spacer(modifier = Modifier.height(8.dp)) }
        }

        // ── Get Personalised Updates ──────────────────────────────
        item {
            PersonalizedCard(
                name     = state.userName,
                onUpdate = { viewModel.updateUserName(it) }
            )
            Spacer(modifier = Modifier.height(16.dp))
        }

        // ── Sustainability News ───────────────────────────────────
        item {
            Text(
                "Sustainability News",
                style     = MaterialTheme.typography.titleMedium,
                modifier  = Modifier.padding(horizontal = 20.dp, vertical = 4.dp)
            )
        }
        items(SampleData.news, key = { it.id }) { news ->
            NewsRow(news)
        }
        item { Spacer(modifier = Modifier.height(16.dp)) }
    }
}

// ── Expandable Search Card ─────────────────────────────────────────
@Composable
fun ExpandableSearchCard(
    isExpanded: Boolean,
    searchQuery: String,
    onToggle: () -> Unit,
    onQueryChange: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth(),
        shape  = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
        elevation = CardDefaults.cardElevation(0.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Header row
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onToggle() },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.Search, contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary, modifier = Modifier.size(20.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text("Search Options",
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier.weight(1f))
                Icon(
                    if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                    contentDescription = if (isExpanded) "Collapse" else "Expand",
                    tint = MaterialTheme.colorScheme.primary
                )
            }

            AnimatedVisibility(visible = isExpanded) {
                Column {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        "Select a category to begin your ethical journey.",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    // Search field
                    OutlinedTextField(
                        value = searchQuery,
                        onValueChange = onQueryChange,
                        placeholder = { Text("e.g. Uniqlo, Nike, Zara…") },
                        leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                        singleLine = true,
                        shape = RoundedCornerShape(12.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor   = MaterialTheme.colorScheme.surface,
                            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                            focusedBorderColor      = MaterialTheme.colorScheme.primary,
                            unfocusedBorderColor    = MaterialTheme.colorScheme.outline
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    // Browse All Brands button
                    Button(
                        onClick = { /* already showing inline */ },
                        modifier = Modifier.fillMaxWidth(),
                        shape  = RoundedCornerShape(10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Icon(Icons.Default.Search, contentDescription = null, modifier = Modifier.size(18.dp))
                        Spacer(modifier = Modifier.width(6.dp))
                        Text("Browse All Brands")
                    }
                }
            }
        }
    }
}

// ── Brand Row Item (shown inline on Home when search is expanded) ──
@Composable
fun BrandRowItem(brand: Brand, onFavoriteToggle: () -> Unit, onClick: () -> Unit) {
    val ratingColor = ratingColor(brand.rating)

    Card(
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 4.dp)
            .fillMaxWidth()
            .clickable { onClick() },
        shape  = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Logo
            Image(
                painter = painterResource(brand.logoRes),
                contentDescription = brand.name,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(52.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(brand.name, style = MaterialTheme.typography.titleSmall, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(3.dp))
                RatingBadge(brand.rating, ratingColor)
                Spacer(modifier = Modifier.height(3.dp))
                Text(brand.description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 2)
            }
            IconButton(onClick = onFavoriteToggle) {
                Icon(
                    if (brand.isFavorite) Icons.Default.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = "Favourite",
                    tint = if (brand.isFavorite) Color(0xFFE63946) else MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
        }
    }
}

// ── Personalised Card ─────────────────────────────────────────────
@Composable
fun PersonalizedCard(name: String, onUpdate: (String) -> Unit) {
    var text by remember { mutableStateOf(name) }

    Card(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth(),
        shape  = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Get Personalised Updates", style = MaterialTheme.typography.titleSmall)
            Spacer(modifier = Modifier.height(10.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                OutlinedTextField(
                    value = text,
                    onValueChange = { text = it },
                    placeholder = { Text("Enter name") },
                    singleLine = true,
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.weight(1f)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Button(
                    onClick = { onUpdate(text) },
                    shape  = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Icon(Icons.Default.Check, contentDescription = null, modifier = Modifier.size(16.dp))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Update")
                }
            }
        }
    }
}

// ── News Row ──────────────────────────────────────────────────────
@Composable
fun NewsRow(news: NewsItem) {
    Card(
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 4.dp)
            .fillMaxWidth(),
        shape  = RoundedCornerShape(14.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(1.dp)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(news.title, style = MaterialTheme.typography.bodyMedium, fontWeight = FontWeight.Bold)
                Text(news.timeAgo, style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant)
            }
            Spacer(modifier = Modifier.width(12.dp))
            Image(
                painter = painterResource(news.imageRes),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(56.dp)
                    .clip(RoundedCornerShape(10.dp))
            )
        }
    }
}

// ── Shared helpers ────────────────────────────────────────────────
fun ratingColor(rating: String) = when (rating) {
    "Great"           -> RatingGreat
    "It's a Start"    -> RatingStart
    "Not Good Enough" -> RatingBad
    else              -> Color.Gray
}

@Composable
fun RatingBadge(rating: String, color: Color) {
    Surface(
        color = color,
        shape = RoundedCornerShape(20.dp)
    ) {
        Text(
            rating,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 3.dp),
            style    = MaterialTheme.typography.labelMedium,
            color    = White
        )
    }
}