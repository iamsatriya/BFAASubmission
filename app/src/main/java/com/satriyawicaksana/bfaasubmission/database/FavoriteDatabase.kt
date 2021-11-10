package com.satriyawicaksana.bfaasubmission.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Favorite::class], version = 1, exportSchema = false)
abstract class FavoriteDatabase : RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao

    companion object {
        @Volatile
        private var INSTANCE: FavoriteDatabase? = null

        @JvmStatic
        fun getDatabase(application: Application): FavoriteDatabase {
            if (INSTANCE == null) {
                synchronized(FavoriteDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        application,
                        FavoriteDatabase::class.java,
                        "favorite_database"
                    ).build()
                }
            }
            return INSTANCE as FavoriteDatabase
        }
    }
}