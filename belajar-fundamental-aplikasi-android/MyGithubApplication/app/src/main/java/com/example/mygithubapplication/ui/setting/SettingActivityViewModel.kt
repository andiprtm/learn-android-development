package com.example.mygithubapplication.ui.setting

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.mygithubapplication.data.preference.SettingPreferences
import kotlinx.coroutines.launch

class SettingActivityViewModel(private val pref: SettingPreferences) : ViewModel() {
    fun getThemeSettings(): LiveData<Boolean> {
        return pref.getThemeSetting().asLiveData()
    }

    fun saveThemeSetting(isLightModeActive: Boolean) {
        viewModelScope.launch {
            pref.saveThemeSetting(isLightModeActive)
        }
    }
}