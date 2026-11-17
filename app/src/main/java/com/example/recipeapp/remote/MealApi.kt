package com.example.recipeapp.remote

import retrofit2.http.Query // ✅ من Retrofit
import retrofit2.http.GET

interface MealApi {
    @GET("random.php")
    suspend fun getRandomMeal(): MealResponse
    @GET("search.php")
    suspend fun searchMeals(
        @Query("s") query: String
    ): MealResponse
    @GET("lookup.php")
    suspend fun getMealById(
        @Query("i") id: String
    ): MealResponse


}