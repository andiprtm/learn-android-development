package com.example.mysettingpreference

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat

class MyPreferenceFragment: PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preference)
    }
}