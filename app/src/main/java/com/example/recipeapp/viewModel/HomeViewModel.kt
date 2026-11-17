package com.example.recipeapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.remote.Meal
import com.example.recipeapp.repository.MealRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = MealRepository(application = application)
    private val _uiState = MutableStateFlow<UIState>(UIState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        getRandomMeals()
    }

    fun getRandomMeals() {
        viewModelScope.launch {
            try {
                _uiState.value = UIState.Loading
                val meals = mutableListOf<Meal>()

                // Fetch 15 random meals
                repeat(15) {
                    val response = repository.getRandomMeal()
                    response.meals?.firstOrNull()?.let {
                        if (meals.none { existing -> existing.idMeal == it.idMeal }) {
                            meals.add(it)
                        }
                    }
                }
                _uiState.value = UIState.Success(meals)
            } catch (e: Exception) {
                _uiState.value = UIState.Error(e.message ?: "Failed to load recipes. Please check your connection.")
            }
        }
    }
}

sealed class UIState {
    data object Loading : UIState()
    data class Success(val meals: List<Meal>) : UIState()
    data class Error(val message: String) : UIState()
}
