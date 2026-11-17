package com.example.recipeapp.repository

import android.app.Application
import com.example.recipeapp.localDataBase.MealDB
import com.example.recipeapp.localDataBase.MealDatabase
import com.example.recipeapp.remote.MealResponse
import com.example.recipeapp.remote.RetrofitInstance
import kotlinx.coroutines.flow.Flow

class MealRepository(application: Application) {
    //api
    private val api = RetrofitInstance.api
    //room
    private val db = MealDatabase.getDatabase(application)
    private val  dao = db.getDao()
    /*
    ----------------------------
    * Repository api
    ---------------------------
    * */
    suspend fun getRandomMeal(): MealResponse{
        return api.getRandomMeal()
    }
    suspend fun searchMeal(query: String): MealResponse{
        return api.searchMeals(query)
    }
    suspend fun getMealById(id: String): MealResponse{
        return api.getMealById(id)
    }
    /*
    ____________________
    /Room database
    ____________________
     */
    fun getFavoriteMeals(): Flow<List<MealDB>>{
        return dao.getAllMeal()
    }
    suspend fun addMeal(mealDB: MealDB){
        dao.addMeal(mealDB)
    }
    suspend fun deleteMeal(mealDB: MealDB){
        dao.deleteAll(mealDB)
    }

}