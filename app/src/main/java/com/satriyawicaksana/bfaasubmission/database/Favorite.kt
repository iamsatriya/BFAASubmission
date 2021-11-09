package com.satriyawicaksana.bfaasubmission.database

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity
@Parcelize
data class Favorite(
    @PrimaryKey
    @ColumnInfo(name="login")
    @NonNull
    val login: String,
    @ColumnInfo(name="avatar_url")
    val avatarUrl: String?,
): Parcelable

