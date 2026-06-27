package com.example.a212019_nurinfarhana_drrimaniza_project1.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.a212019_nurinfarhana_drrimaniza_project1.EthicViewModel
import com.example.a212019_nurinfarhana_drrimaniza_project1.screens.*

sealed class Screen(val route: String) {
    object Home        : Screen("home")
    object Shop        : Screen("shop")
    object Favourites  : Screen("favourites")
    object Cart        : Screen("cart")
    object Profile     : Screen("profile")
    object BrandDetail : Screen("brand_detail")
    object ItemDetail  : Screen("item_detail")
}

data class BottomNavItem(
    val route: String,
    val label: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector
)

val bottomNavItems = listOf(
    BottomNavItem(Screen.Home.route,       "Home",       Icons.Filled.Home,         Icons.Outlined.Home),
    BottomNavItem(Screen.Shop.route,       "Shop",       Icons.Filled.ShoppingBag,  Icons.Outlined.ShoppingBag),
    BottomNavItem(Screen.Favourites.route, "Favourites", Icons.Filled.Favorite,     Icons.Outlined.FavoriteBorder),
    BottomNavItem(Screen.Cart.route,       "Cart",       Icons.Filled.ShoppingCart, Icons.Outlined.ShoppingCart),
    BottomNavItem(Screen.Profile.route,    "Profile",    Icons.Filled.Person,       Icons.Outlined.Person),
)

@Composable
fun NavGraph(navController: NavHostController, viewModel: EthicViewModel) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val navBackStack by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStack?.destination?.route

    val showBottomBar = currentRoute in bottomNavItems.map { it.route }

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                NavigationBar(
                    containerColor = MaterialTheme.colorScheme.surface,
                    tonalElevation = androidx.compose.ui.unit.Dp(0f)
                ) {
                    bottomNavItems.forEach { item ->
                        val selected = currentRoute == item.route
                        NavigationBarItem(
                            selected = selected,
                            onClick = {
                                if (!selected) {
                                    navController.navigate(item.route) {
                                        popUpTo(Screen.Home.route) { saveState = true }
                                        launchSingleTop = true
                                        restoreState = true
                                    }
                                }
                            },
                            icon = {
                                BadgedBox(badge = {
                                    if (item.route == Screen.Cart.route && state.cartItems.isNotEmpty()) {
                                        Badge { Text(state.cartItems.size.toString()) }
                                    }
                                }) {
                                    Icon(
                                        if (selected) item.selectedIcon else item.unselectedIcon,
                                        contentDescription = item.label
                                    )
                                }
                            },
                            label = { Text(item.label, style = MaterialTheme.typography.labelSmall) },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor   = MaterialTheme.colorScheme.primary,
                                selectedTextColor   = MaterialTheme.colorScheme.primary,
                                indicatorColor      = MaterialTheme.colorScheme.primaryContainer,
                                unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                                unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController     = navController,
            startDestination  = Screen.Home.route,
            modifier          = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route)        { HomeScreen(navController, viewModel) }
            composable(Screen.Shop.route)        { ShopScreen(navController, viewModel) }
            composable(Screen.Favourites.route)  { FavouritesScreen(navController, viewModel) }
            composable(Screen.Cart.route)        { CartScreen(navController, viewModel) }
            composable(Screen.Profile.route)     { ProfileScreen(navController, viewModel) }
            composable(Screen.BrandDetail.route) { BrandDetailScreen(navController, viewModel) }
            composable(Screen.ItemDetail.route)  { ItemDetailScreen(navController, viewModel) }
        }
    }
}