package com.example.recipeapp.remote

data class MealResponse(
    val meals: List<Meal>? = null
)

data class Meal(
    val idMeal: String,
    val strMeal: String,
    val strCategory: String? = null,
    val strArea: String? = null,
    val strInstructions: String? = null,
    val strMealThumb: String? = null
)
