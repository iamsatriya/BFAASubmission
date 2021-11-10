package com.satriyawicaksana.bfaasubmission.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(favorite: Favorite)

    @Delete
    fun delete(favorite: Favorite)

    @Query("SELECT * FROM favorite ORDER BY login ASC")
    fun getAllFavorites():LiveData<List<Favorite>>

    @Query("SELECT * FROM favorite WHERE favorite.login = :login")
    fun getSelecteduser(login: String): Favorite?
}