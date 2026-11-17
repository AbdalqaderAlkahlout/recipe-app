package com.example.recipeapp.localDataBase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface DaoMeal {
    @Query("SELECT * FROM  meal_recipe")
    fun  getAllMeal (): Flow<List<MealDB>>
    @Insert
    suspend fun addMeal(mealDB: MealDB)
    @Delete
    suspend fun deleteAll(mealDB: MealDB)
}