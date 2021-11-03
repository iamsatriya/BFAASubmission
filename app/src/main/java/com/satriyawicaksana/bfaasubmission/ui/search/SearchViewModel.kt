package com.satriyawicaksana.bfaasubmission.ui.search

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.satriyawicaksana.bfaasubmission.pojo.ItemsItem
import com.satriyawicaksana.bfaasubmission.pojo.ResponseDetailUser
import com.satriyawicaksana.bfaasubmission.pojo.ResponseSearchUser
import com.satriyawicaksana.bfaasubmission.screen.userprofile.DetailProfileActivity
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
//                            _listUser.value?.add(newUser)
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
//
//    fun openUserDetail(username: String, context: Context) {
//        val client = ApiConfig.getApiService().getUserDetail(username)
//        client.enqueue(object : Callback<ResponseDetailUser> {
//            override fun onResponse(
//                call: Call<ResponseDetailUser>,
//                response: Response<ResponseDetailUser>
//            ) {
//                if (response.isSuccessful) {
//                    val mIntent = Intent(context, DetailProfileActivity::class.java)
//                    mIntent.putExtra(DetailProfileActivity.EXTRA_USER, response.body())
//                    context.startActivity(mIntent)
//                } else {
//                    Log.e(TAG, "onResponse: ${response.message()}")
//                }
//            }
//
//            override fun onFailure(call: Call<ResponseDetailUser>, t: Throwable) {
//                Log.e(TAG, "onFailure: ${t.message}")
//            }
//        })
//    }
}