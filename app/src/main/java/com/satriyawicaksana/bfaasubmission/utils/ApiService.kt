package com.satriyawicaksana.bfaasubmission.utils

import com.satriyawicaksana.bfaasubmission.pojo.ResponseSearchUser
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    fun getUserList(@Query("q") username: String):Call<ResponseSearchUser>
}