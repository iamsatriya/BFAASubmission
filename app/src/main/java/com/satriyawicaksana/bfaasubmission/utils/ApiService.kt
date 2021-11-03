package com.satriyawicaksana.bfaasubmission.utils

import com.satriyawicaksana.bfaasubmission.pojo.ItemsItem
import com.satriyawicaksana.bfaasubmission.pojo.ResponseDetailUser
import com.satriyawicaksana.bfaasubmission.pojo.ResponseSearchUser
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    fun getUserList(@Query("q") username: String):Call<ResponseSearchUser>

    @GET("users/{username}")
    fun getUserDetail(@Path("username") username: String):Call<ResponseDetailUser>

    @GET("users/{username}/followers")
    fun getUserFollower(@Path("username") username: String?): Call<ArrayList<ItemsItem>>

    @GET("users/{username}/following")
    fun getUserFollowing(@Path("username") username: String?): Call<ArrayList<ItemsItem>>
}