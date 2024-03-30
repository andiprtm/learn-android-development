package com.example.mygithubapplication.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mygithubapplication.data.favorite.entity.UserEntity
import com.example.mygithubapplication.data.remote.response.DetailUserResponse
import com.example.mygithubapplication.data.remote.retrofit.ApiConfig
import com.example.mygithubapplication.repository.FavoriteRepository
import com.example.mygithubapplication.util.Event
import retrofit2.Callback
import retrofit2.Response

class DetailActivityViewModel(private val favoriteRepository: FavoriteRepository) : ViewModel() {
    private val _userDetail = MutableLiveData<DetailUserResponse>()
    val userDetail: LiveData<DetailUserResponse> = _userDetail

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _snackbarText = MutableLiveData<Event<String>>()
    val snackbarText: LiveData<Event<String>> = _snackbarText

    fun insert(user: UserEntity) {
        favoriteRepository.insert(user)
    }

    fun delete(id: Int) {
        favoriteRepository.delete(id)
    }

    fun getAllFavorites() = favoriteRepository.getAllFavorites()

    fun getDetailUserData(username: String) {
        _isLoading.value = true

        val client = ApiConfig.getApiService().getUserDetail(username)
        client.enqueue(object : Callback<DetailUserResponse> {
            override fun onResponse(
                call: retrofit2.Call<DetailUserResponse>,
                response: Response<DetailUserResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful) {
                    _userDetail.value = response.body()
                } else {
                    Log.d(TAG, "on failure: ${response.message()}")
                }
            }

            override fun onFailure(call: retrofit2.Call<DetailUserResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
                _snackbarText.value = Event("Data failed to load, please try again.")
            }

        })
    }

    companion object {
        private const val TAG = "DetailActivityMainModel"
    }
}