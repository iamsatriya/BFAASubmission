package com.satriyawicaksana.bfaasubmission.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.satriyawicaksana.bfaasubmission.MainViewModel
import com.satriyawicaksana.bfaasubmission.ui.setting.SettingViewModel
import java.lang.IllegalArgumentException

class ViewModelFactory(private val preference: SettingPreference) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(preference) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}