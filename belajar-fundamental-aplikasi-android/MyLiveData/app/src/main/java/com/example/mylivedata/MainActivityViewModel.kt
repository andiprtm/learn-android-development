package com.example.mylivedata

import android.os.SystemClock
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.Timer
import java.util.TimerTask

class MainActivityViewModel: ViewModel() {

    private val mInitialTime = SystemClock.elapsedRealtime()
    private val mElapsedTime = MutableLiveData<Long?>()
    fun getElapsedTime(): LiveData<Long?>{
        return mElapsedTime
    }

    init {
        val timer = Timer()
        timer.scheduleAtFixedRate(object : TimerTask(){
            override fun run() {
                val newValue = (SystemClock.elapsedRealtime() - mInitialTime) / 1000
                mElapsedTime.postValue(newValue)
            }

        }, ONE_SECONDS.toLong(), ONE_SECONDS.toLong())
    }

    companion object{
        private const val ONE_SECONDS = 1000
    }
}
