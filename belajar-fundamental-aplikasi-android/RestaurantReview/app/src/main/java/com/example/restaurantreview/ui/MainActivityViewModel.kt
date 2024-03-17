package com.example.restaurantreview.ui

import Event
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.restaurantreview.MainActivity
import com.example.restaurantreview.data.response.CustomerReviewsItem
import com.example.restaurantreview.data.response.Response
import com.example.restaurantreview.data.response.ResponsePost
import com.example.restaurantreview.data.response.Restaurant
import com.example.restaurantreview.data.retrofit.ApiConfig
import retrofit2.Call
import retrofit2.Callback

class MainActivityViewModel: ViewModel() {

    private val _restaurant = MutableLiveData<Restaurant>()
    val restaurant: LiveData<Restaurant> = _restaurant

    private val _listReview = MutableLiveData<List<CustomerReviewsItem>>()
    val listReview: LiveData<List<CustomerReviewsItem>> = _listReview

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _snackbarText = MutableLiveData<Event<String>>()
    val snackbarText: LiveData<Event<String>> = _snackbarText

    init {
        findRestaurant()
    }

    private fun findRestaurant() {
        _isLoading.value = true

        val client = ApiConfig.GetApiService().getRestaurant(RESTAURANT_ID)
        client.enqueue(object : Callback<Response> {
            override fun onResponse(call: Call<Response>, response: retrofit2.Response<Response>) {
                _isLoading.value = false

                if (response.isSuccessful) {
                    _restaurant.value = response.body()?.restaurant
                    _listReview.value = response.body()?.restaurant?.customerReviews
                } else {
                    Log.d(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<Response>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }

    fun postReview(input: String) {
        _isLoading.value = true
        val client = ApiConfig.GetApiService().postReview(RESTAURANT_ID, "Dicoding", input)

        client.enqueue(object : Callback<ResponsePost> {
            override fun onResponse(
                call: Call<ResponsePost>,
                response: retrofit2.Response<ResponsePost>
            ) {
                _isLoading.value = false

                val responBody = response.body()

                if (response.isSuccessful && responBody != null) {
                    _listReview.value = response.body()?.customerReviews
                    _snackbarText.value = Event(response.body()?.message.toString())
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ResponsePost>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }

    companion object{
        private const val TAG = "MainViewModel"
        private const val RESTAURANT_ID = "uewq1zg2zlskfw1e867"
    }
}