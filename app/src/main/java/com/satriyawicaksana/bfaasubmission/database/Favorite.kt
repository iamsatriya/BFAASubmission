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
//data class Favorite(
//    @PrimaryKey(autoGenerate = true)
//    @ColumnInfo(name="id")
//    val id: Int,
//    @ColumnInfo(name="avatar_url")
//    val avatarUrl: String?,
//    @ColumnInfo(name="login")
//    val login: String?,
//    @ColumnInfo(name="public_repos")
//    val publicRepos: Int?,
//    @ColumnInfo(name="full_name")
//    val name: String?,
//    @ColumnInfo(name="company")
//    val company: String?,
//    @ColumnInfo(name="location")
//    val location: String?,
//    @ColumnInfo(name="email")
//    val email: String?,
//    @ColumnInfo(name="followers")
//    val followers: Int,
//    @ColumnInfo(name="following")
//    val following: Int,
//    @ColumnInfo(name="html_url")
//    val htmlUrl: String,
//): Parcelable
