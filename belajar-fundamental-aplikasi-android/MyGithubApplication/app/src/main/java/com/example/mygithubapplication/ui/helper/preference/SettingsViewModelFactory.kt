package com.example.mygithubapplication.ui.helper.preference

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mygithubapplication.data.preference.SettingPreferences
import com.example.mygithubapplication.ui.home.MainActivityViewModel
import com.example.mygithubapplication.ui.setting.SettingActivityViewModel

class SettingsViewModelFactory(private val pref: SettingPreferences) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainActivityViewModel::class.java)) {
            return MainActivityViewModel(pref) as T
        } else if (modelClass.isAssignableFrom(SettingActivityViewModel::class.java)) {
            return SettingActivityViewModel(pref) as T
        }

        throw IllegalArgumentException("Unknown ViewModel Class : ${modelClass.name}")
    }
}