package com.example.myviewmodel

import android.health.connect.datatypes.units.Length
import androidx.lifecycle.ViewModel

class ActivityMainViewModel: ViewModel() {

    var result = 0

    fun calculate(width: String, length: String, height: String){
        result = width.toInt() * length.toInt() * height.toInt()
    }
}