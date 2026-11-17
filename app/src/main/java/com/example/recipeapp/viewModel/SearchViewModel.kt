package com.example.recipeapp.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.recipeapp.repository.MealRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SearchViewModel(application: Application) : AndroidViewModel(application) {
    private val searchRepository = MealRepository(application)

    private val _searchChange = MutableStateFlow("")
    val searchChange = _searchChange.asStateFlow()

    private val _uiState = MutableStateFlow<UIState>(UIState.Success(emptyList()))
    val uiState = _uiState.asStateFlow()

    fun onSearchChange(query: String) {
        _searchChange.value = query
    }

    fun getSearch(query: String) {
        val trimmed = query.trim()
        if (trimmed.isEmpty()) {
            _uiState.value = UIState.Success(emptyList())
            return
        }
        viewModelScope.launch {
            try {
                _uiState.value = UIState.Loading
                val response = searchRepository.searchMeal(trimmed)
                _uiState.value = UIState.Success(response.meals ?: emptyList())
            } catch (e: Exception) {
                _uiState.value = UIState.Error(e.message ?: "Failed to find recipes. Please check your connection.")
            }
        }
    }
}
