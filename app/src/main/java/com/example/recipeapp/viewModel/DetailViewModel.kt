package com.example.recipeapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.localDataBase.MealDB
import com.example.recipeapp.remote.Meal
import com.example.recipeapp.repository.MealRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DetailViewModel(application: Application) : AndroidViewModel(application) {
    private val detailRepository = MealRepository(application)
    private val _uiState = MutableStateFlow<UIState>(UIState.Loading)
    val uiState = _uiState.asStateFlow()
    private val _isFavorite = MutableStateFlow(false)
    val isFavorite = _isFavorite.asStateFlow()

    fun getById(id: String) {
        viewModelScope.launch {
            try {
                _uiState.value = UIState.Loading
                val response = detailRepository.getMealById(id)
                _uiState.value = UIState.Success(response.meals ?: emptyList())
            } catch (e: Exception) {
                _uiState.value = UIState.Error(e.message ?: "Failed to load recipe details.")
            }
        }
    }

    fun toggleFavorite(meal: Meal) {
        viewModelScope.launch {
            val mealDb = MealDB(
                idMeal = meal.idMeal,
                strMeal = meal.strMeal,
                strCategory = meal.strCategory ?: "",
                strArea = meal.strArea ?: "",
                strInstructions = meal.strInstructions ?: "",
                strMealThumb = meal.strMealThumb ?: ""
            )
            if (_isFavorite.value) {
                detailRepository.deleteMeal(mealDb)
            } else {
                detailRepository.addMeal(mealDb)
            }
            _isFavorite.value = !_isFavorite.value
        }
    }

    fun checkIfFavorite(mealId: String) {
        viewModelScope.launch {
            detailRepository.getFavoriteMeals().collect { favorites ->
                _isFavorite.value = favorites.any { it.idMeal == mealId }
            }
        }
    }
}
