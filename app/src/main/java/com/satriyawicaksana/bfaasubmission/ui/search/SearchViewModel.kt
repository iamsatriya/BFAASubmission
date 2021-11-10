package com.satriyawicaksana.bfaasubmission.ui.search

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.satriyawicaksana.bfaasubmission.pojo.ItemsItem
import com.satriyawicaksana.bfaasubmission.pojo.ResponseSearchUser
import com.satriyawicaksana.bfaasubmission.utils.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SearchViewModel : ViewModel() {
    companion object {
        private val TAG = SearchViewModel::class.java.simpleName
    }

    private val _listUser = MutableLiveData<ArrayList<ItemsItem>>()
    val listUser: LiveData<ArrayList<ItemsItem>> = _listUser

    fun setListUser(username: String) {
        val list = ArrayList<ItemsItem>()
        val client = ApiConfig.getApiService().getUserList(username)
        client.enqueue(object : Callback<ResponseSearchUser> {
            override fun onResponse(
                call: Call<ResponseSearchUser>,
                response: Response<ResponseSearchUser>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        for (user in responseBody.items) {
                            val newUser =
                                ItemsItem(user.avatarUrl, user.htmlUrl, user.id, user.login)
                            list.add(newUser)
                        }
                        _listUser.postValue(list)
                    }
                } else {
                    Log.e(TAG, "onResponseFailed: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ResponseSearchUser>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }
}