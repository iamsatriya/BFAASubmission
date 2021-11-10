package com.satriyawicaksana.bfaasubmission.screen.userprofile

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.satriyawicaksana.bfaasubmission.database.Favorite
import com.satriyawicaksana.bfaasubmission.pojo.ItemsItem
import com.satriyawicaksana.bfaasubmission.pojo.ResponseDetailUser
import com.satriyawicaksana.bfaasubmission.repository.FavoriteRepository
import com.satriyawicaksana.bfaasubmission.utils.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailProfileViewModel(application: Application) : ViewModel() {
    private var _userDetail = MutableLiveData<ResponseDetailUser>()
    val userDetail: LiveData<ResponseDetailUser> = _userDetail

    private var _listFollower = MutableLiveData<ArrayList<ItemsItem>>()
    val listFollower: LiveData<ArrayList<ItemsItem>> = _listFollower

    private var _listFollowing = MutableLiveData<ArrayList<ItemsItem>>()
    val listFollowing: LiveData<ArrayList<ItemsItem>> = _listFollowing

    private val mFavoriteUserRepository: FavoriteRepository =
        FavoriteRepository(application)

    fun insert() {
        val favoriteUser =
            Favorite(
                _userDetail.value?.login!!,
                _userDetail.value?.avatarUrl
            )
        mFavoriteUserRepository.insert(favoriteUser)
    }

    fun delete() {
        val favoriteUser =
            Favorite(
                _userDetail.value?.login!!,
                _userDetail.value?.avatarUrl
            )
        mFavoriteUserRepository.delete(favoriteUser)
    }

    fun getAllFavorite() {

    }

    private fun fetchFollower() {
        val client = ApiConfig.getApiService().getUserFollower(_userDetail.value?.login)
        client.enqueue(object : Callback<ArrayList<ItemsItem>> {
            override fun onResponse(
                call: Call<ArrayList<ItemsItem>>,
                response: Response<ArrayList<ItemsItem>>
            ) {
                _listFollower.value = response.body()
            }

            override fun onFailure(call: Call<ArrayList<ItemsItem>>, t: Throwable) {
                Log.e("DetailViewModel", "onFailure: ${t.message}")
            }
        })
    }

    private fun fetchFollowing() {
        val client = ApiConfig.getApiService().getUserFollowing(_userDetail.value?.login)
        client.enqueue(object : Callback<ArrayList<ItemsItem>> {
            override fun onResponse(
                call: Call<ArrayList<ItemsItem>>,
                response: Response<ArrayList<ItemsItem>>
            ) {
                _listFollowing.value = response.body()
            }

            override fun onFailure(call: Call<ArrayList<ItemsItem>>, t: Throwable) {
                Log.e("DetailViewModel", "onFailure: ${t.message}")
            }

        })
    }

    fun setUser(user: ResponseDetailUser) {
        _userDetail.value = user
        fetchFollower()
        fetchFollowing()
    }
}