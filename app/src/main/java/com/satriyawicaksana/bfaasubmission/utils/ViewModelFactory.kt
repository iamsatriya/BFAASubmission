package com.satriyawicaksana.bfaasubmission.utils

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.satriyawicaksana.bfaasubmission.MainViewModel
import com.satriyawicaksana.bfaasubmission.screen.userprofile.DetailProfileViewModel
import com.satriyawicaksana.bfaasubmission.ui.setting.SettingViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(private val preference: SettingPreference) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(preference) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}

class FavoriteViewModelFactory private constructor(private val mApplication: Application) :
    ViewModelProvider.NewInstanceFactory() {
    companion object {
       @Volatile
       private var INSTANCE: FavoriteViewModelFactory? = null

        @JvmStatic
        fun getInstance(application: Application): FavoriteViewModelFactory{
            if (INSTANCE == null) {
                synchronized(FavoriteViewModelFactory::class.java){
                    INSTANCE = FavoriteViewModelFactory(application)
                }
            }
            return INSTANCE as FavoriteViewModelFactory
        }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailProfileViewModel::class.java)){
            return DetailProfileViewModel(mApplication) as T
        } else if (modelClass.isAssignableFrom(FavoriteViewModelFactory::class.java)){
            return FavoriteViewModelFactory(mApplication) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}