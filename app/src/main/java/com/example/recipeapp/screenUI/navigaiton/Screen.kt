package com.example.recipeapp.screenUI.navigaiton

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.RestaurantMenu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.RestaurantMenu
import androidx.compose.material.icons.outlined.Search
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(
    val route: String,
    val icon: ImageVector,
    val selectedIcon: ImageVector,
    val title: String
) {
    data object Home : Screen(
        route = "home",
        icon = Icons.Outlined.Home,
        selectedIcon = Icons.Filled.Home,
        title = "Home"
    )

    data object Favorite : Screen(
        route = "favorite",
        icon = Icons.Outlined.FavoriteBorder,
        selectedIcon = Icons.Filled.Favorite,
        title = "Favorites"
    )

    data object Search : Screen(
        route = "search",
        icon = Icons.Outlined.Search,
        selectedIcon = Icons.Filled.Search,
        title = "Search"
    )

    data object Detail : Screen(
        route = "detail/{mealId}",
        icon = Icons.Outlined.RestaurantMenu,
        selectedIcon = Icons.Filled.RestaurantMenu,
        title = "Detail"
    ) {
        fun createRoute(mealId: String) = "detail/$mealId"
        fun createRuote(mealId: String) = createRoute(mealId) // Backwards compatibility
    }
}
