package com.example.recipeapp.localDataBase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "meal_recipe")
data class MealDB(
    @PrimaryKey
    val idMeal: String,
    val strMeal: String,
    val strCategory: String,
    val strArea: String,
    val strInstructions: String,
    val strMealThumb: String
)
