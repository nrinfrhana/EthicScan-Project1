package com.example.a212019_nurinfarhana_drrimaniza_project1

import androidx.lifecycle.ViewModel
import com.example.a212019_nurinfarhana_drrimaniza_project1.data.Brand
import com.example.a212019_nurinfarhana_drrimaniza_project1.data.ClothingItem
import com.example.a212019_nurinfarhana_drrimaniza_project1.data.SampleData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class EthicUiState(
    // User profile
    val userName: String = "Guest",

    // Brands
    val brands: List<Brand>           = SampleData.brands,
    val brandSearchQuery: String      = "",
    val filteredBrands: List<Brand>   = SampleData.brands,
    val selectedBrand: Brand?         = null,
    val isSearchExpanded: Boolean     = false,

    // Shop
    val clothingItems: List<ClothingItem>  = SampleData.clothingItems,
    val shopSearchQuery: String            = "",
    val filteredItems: List<ClothingItem>  = SampleData.clothingItems,
    val selectedCategory: String           = "All",
    val selectedItem: ClothingItem?        = null,

    // Cart
    val cartItems: List<ClothingItem> = emptyList()
)

class EthicViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(EthicUiState())
    val uiState: StateFlow<EthicUiState> = _uiState.asStateFlow()

    val shopCategories = listOf("All", "T-Shirt", "Jeans", "Cardigan", "Top", "Pants", "Hoodie", "Jacket", "Dress")

    // Profile
    fun updateUserName(name: String) = _uiState.update { it.copy(userName = name) }

    // Brand search & expand
    fun toggleSearchExpanded() = _uiState.update { it.copy(isSearchExpanded = !it.isSearchExpanded) }

    fun updateBrandSearch(query: String) {
        val filtered = if (query.isBlank()) SampleData.brands
        else SampleData.brands.filter { it.name.contains(query, ignoreCase = true) }
        _uiState.update { it.copy(brandSearchQuery = query, filteredBrands = filtered) }
    }

    fun selectBrand(brand: Brand) = _uiState.update { it.copy(selectedBrand = brand) }

    fun toggleFavorite(brandId: Int) {
        fun List<Brand>.toggled() = map { if (it.id == brandId) it.copy(isFavorite = !it.isFavorite) else it }
        _uiState.update {
            it.copy(
                brands        = it.brands.toggled(),
                filteredBrands = it.filteredBrands.toggled()
            )
        }
    }

    val favoriteBrands get() = _uiState.value.brands.filter { it.isFavorite }

    // Shop
    fun updateShopSearch(query: String) = _uiState.update { state ->
        state.copy(shopSearchQuery = query, filteredItems = filterItems(query, state.selectedCategory))
    }

    fun selectCategory(category: String) = _uiState.update { state ->
        state.copy(selectedCategory = category, filteredItems = filterItems(state.shopSearchQuery, category))
    }

    private fun filterItems(query: String, category: String) = SampleData.clothingItems.filter {
        (query.isBlank() || it.name.contains(query, ignoreCase = true)) &&
                (category == "All" || it.category == category)
    }

    fun selectItem(item: ClothingItem) = _uiState.update { it.copy(selectedItem = item) }

    // Cart
    fun addToCart(item: ClothingItem) {
        if (_uiState.value.cartItems.none { it.id == item.id })
            _uiState.update { it.copy(cartItems = it.cartItems + item) }
    }

    fun removeFromCart(item: ClothingItem) =
        _uiState.update { it.copy(cartItems = it.cartItems.filter { c -> c.id != item.id }) }

    val cartTotal get() = _uiState.value.cartItems.sumOf { it.salePrice }
}