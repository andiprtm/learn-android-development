package com.example.mygithubapplication.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mygithubapplication.data.response.GithubResponse
import com.example.mygithubapplication.data.response.ItemsItem
import com.example.mygithubapplication.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivityViewModel : ViewModel() {

    private val _listGithubUser = MutableLiveData<List<ItemsItem>>()
    val listGithubUser: LiveData<List<ItemsItem>> = _listGithubUser

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    init {
        findUser("a")
    }

    private fun findUser(username: String) {
        _isLoading.value = true

        val client = ApiConfig.getApiService().getSearchUser(username)
        client.enqueue(object : Callback<GithubResponse> {
            override fun onResponse(
                call: Call<GithubResponse>,
                response: Response<GithubResponse>
            ) {
                _isLoading.value = false

                if (response.isSuccessful) {
                    _listGithubUser.value = response.body()?.items
                } else {
                    Log.d(TAG, "$response.message()")
                }
            }

            override fun onFailure(call: Call<GithubResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }

    companion object {
        private const val TAG = "MainActivityViewModel"
    }
}