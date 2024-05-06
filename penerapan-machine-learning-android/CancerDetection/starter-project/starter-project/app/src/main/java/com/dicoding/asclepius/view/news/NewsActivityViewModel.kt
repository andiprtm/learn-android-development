package com.dicoding.asclepius.view.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.asclepius.BuildConfig
import com.dicoding.asclepius.data.remote.response.ArticlesItem
import com.dicoding.asclepius.data.remote.retrofit.ApiConfig
import com.dicoding.asclepius.utils.Event
import kotlinx.coroutines.launch
import retrofit2.HttpException

class NewsActivityViewModel : ViewModel() {
    private val _listNews = MutableLiveData<List<ArticlesItem>?>()
    val listNews: LiveData<List<ArticlesItem>?> = _listNews

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _snackbarText = MutableLiveData<Event<String>>()
    val snackbarText: LiveData<Event<String>> = _snackbarText

    init {
        getNews()
    }

    fun getNews() {
        _isLoading.value = true

        viewModelScope.launch {
            try {
                val apiService = ApiConfig.getApiService()
                val successResponse = apiService.getCancerNews(
                    "cancer",
                    "health",
                    "en",
                    BuildConfig.TOKEN_NEWS
                )

                val filteredArticles = successResponse.articles?.filter { item ->
                    item?.title != "[Removed]" && item?.description != "[Removed]"
                }

                _listNews.value = filteredArticles as List<ArticlesItem>?

                _isLoading.value = false
            } catch (e: HttpException) {
                _isLoading.value = false
                _snackbarText.value = Event("Error occurred: ${e.message}")
            }
        }
    }
}