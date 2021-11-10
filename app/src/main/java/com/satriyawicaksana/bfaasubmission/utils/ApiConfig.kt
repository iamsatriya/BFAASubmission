package com.satriyawicaksana.bfaasubmission.utils

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {
    companion object {
        fun getApiService(): ApiService {
            val client = OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val newRequest = chain.request().newBuilder()
                        .addHeader("Authorization", GithubAccess.API_KEY)
                        .addHeader("Accept", GithubAccess.ACCEPT)
                        .build()
                    chain.proceed(newRequest)
                }
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return retrofit.create(ApiService::class.java)
        }
    }
}