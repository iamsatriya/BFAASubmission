package com.satriyawicaksana.bfaasubmission.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.satriyawicaksana.bfaasubmission.database.Favorite
import com.satriyawicaksana.bfaasubmission.database.FavoriteDao
import com.satriyawicaksana.bfaasubmission.database.FavoriteDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class FavoriteRepository(application: Application) {
    private val mFavoriteDao: FavoriteDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = FavoriteDatabase.getDatabase(application)
        mFavoriteDao = db.favoriteDao()
    }

    fun getAllFavorites(): LiveData<List<Favorite>> = mFavoriteDao.getAllFavorites()

    fun insert(favorite: Favorite) {
        executorService.execute {mFavoriteDao.insert(favorite)}
    }

    fun delete(favorite: Favorite) {
        executorService.execute { mFavoriteDao.delete(favorite)}
    }
}