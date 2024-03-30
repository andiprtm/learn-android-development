package com.example.mygithubapplication.ui.setting

import android.os.Bundle
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.mygithubapplication.R
import com.example.mygithubapplication.data.preference.SettingPreferences
import com.example.mygithubapplication.data.preference.dataStore
import com.example.mygithubapplication.databinding.ActivitySettingBinding
import com.example.mygithubapplication.ui.helper.preference.SettingsViewModelFactory

class SettingActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val pref = SettingPreferences.getInstance(application.dataStore)
        val settingActivityViewModel = ViewModelProvider(
            this,
            SettingsViewModelFactory(pref)
        )[SettingActivityViewModel::class.java]

        setSupportActionBar(binding.settingToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.title = "Theme Setting"

        settingActivityViewModel.getThemeSettings().observe(this) {
            if (it) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                binding.switch2.isChecked = true
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                binding.switch2.isChecked = false
            }
        }

        binding.switch2.setOnCheckedChangeListener { _: CompoundButton?, isChecked: Boolean ->
            settingActivityViewModel.saveThemeSetting(isChecked)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

}