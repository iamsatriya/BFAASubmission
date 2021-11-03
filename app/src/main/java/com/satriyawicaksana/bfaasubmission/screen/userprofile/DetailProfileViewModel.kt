package com.satriyawicaksana.bfaasubmission.screen.userprofile

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import androidx.room.Room
import com.satriyawicaksana.bfaasubmission.database.Favorite
import com.satriyawicaksana.bfaasubmission.database.FavoriteDao
import com.satriyawicaksana.bfaasubmission.database.FavoriteDatabase
import com.satriyawicaksana.bfaasubmission.pojo.ItemsItem
import com.satriyawicaksana.bfaasubmission.pojo.ResponseDetailUser
import com.satriyawicaksana.bfaasubmission.repository.FavoriteRepository
import com.satriyawicaksana.bfaasubmission.utils.ApiConfig
import kotlinx.coroutines.launch
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

//    private val favoriteDao: FavoriteDao by lazy {
//        FavoriteDatabase.getDatabase(application).favoriteDao()
//    }


    private val mFavoriteUserRepository: FavoriteRepository =
        FavoriteRepository(application)

//    fun insert(favoriteUser: FavoriteUser) = viewModelScope.launch {
//        mFavoriteUserRepository.insert(favoriteUser)
//    }

    fun insert() {
        val favoriteUser =
            Favorite(
                _userDetail.value?.login!!,
                _userDetail.value?.avatarUrl
                //            id = 0,
                //            avatarUrl = _userDetail.value?.avatarUrl,
                //            login = _userDetail.value?.login,
                //            publicRepos = _userDetail.value?.publicRepos,
                //            name = _userDetail.value?.name,
                //            location = _userDetail.value?.location,
                //            company = _userDetail.value?.company,
                //            email = _userDetail.value?.email,
                //            followers = _userDetail.value?.followers ?: 0,
                //            following = _userDetail.value?.following ?: 0,
                //            htmlUrl = _userDetail.value?.htmlUrl ?: "Unknown"
            )

        mFavoriteUserRepository.insert(favoriteUser)
        Log.e("INSERT", "insert: $favoriteUser")

//        viewModelScope.launch {
//            if (favoriteUser != null) {
//                mFavoriteUserRepository.insert(favoriteUser)
//            }
//        }
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
                TODO("Not yet implemented")
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
                TODO("Not yet implemented")
            }

        })
    }

    fun setUser(user: ResponseDetailUser) {
        _userDetail.value = user
        fetchFollower()
        fetchFollowing()
    }
}