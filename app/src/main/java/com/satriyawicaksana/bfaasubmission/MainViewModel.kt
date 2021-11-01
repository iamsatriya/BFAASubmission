package com.satriyawicaksana.bfaasubmission

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.satriyawicaksana.bfaasubmission.utils.SettingPreference
import kotlinx.coroutines.launch

class MainViewModel(private val preferences: SettingPreference): ViewModel() {
    fun getThemeSetting(): LiveData<Boolean> {
        return preferences.getThemeSetting().asLiveData()
    }
    fun saveThemeSetting(isDarkMode: Boolean) {
        viewModelScope.launch {
            preferences.saveThemeSetting(isDarkMode)
        }
    }
}