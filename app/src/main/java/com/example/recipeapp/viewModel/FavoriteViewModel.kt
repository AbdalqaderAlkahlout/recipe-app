package com.example.recipeapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.localDataBase.MealDB
import com.example.recipeapp.repository.MealRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class FavoriteViewModel(application: Application) : AndroidViewModel(application) {
    private val favoriteRepository = MealRepository(application)
    val favoriteMeals: StateFlow<List<MealDB>> = favoriteRepository.getFavoriteMeals()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )

    //delete favorite
    fun deleteMeal(mealDB: MealDB) {
        viewModelScope.launch {
            favoriteRepository.deleteMeal(mealDB)
        }
    }
}