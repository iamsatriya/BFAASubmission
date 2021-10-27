package com.satriyawicaksana.bfaasubmission.ui.setting

import androidx.lifecycle.*
import com.satriyawicaksana.bfaasubmission.utils.SettingPreference
import kotlinx.coroutines.launch

class SettingViewModel(private val pref: SettingPreference) : ViewModel() {
    fun getThemeSettings(): LiveData<Boolean>?{
        return pref.getThemeSetting()?.asLiveData()
    }

    fun saveThemeSetting(isDarkMode: Boolean) {
        viewModelScope.launch {
            pref.saveThemeSetting(isDarkMode)
        }
    }
}