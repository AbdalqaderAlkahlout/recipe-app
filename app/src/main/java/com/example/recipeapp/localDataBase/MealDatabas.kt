package com.example.recipeapp.localDataBase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [MealDB::class], version = 1)
abstract class MealDatabase: RoomDatabase() {
    abstract fun getDao(): DaoMeal

    companion object {
        @Volatile
        private var INSTANCE: MealDatabase? = null
        fun getDatabase(context: Context): MealDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MealDatabase::class.java,
                    "database_meal"
                ).build()
                INSTANCE = instance
                instance
            }

        }


    }
}
