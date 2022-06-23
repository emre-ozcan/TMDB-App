package com.emreozcan.tmdbapp.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emreozcan.tmdbapp.model.response.detail.MovieDetail
import com.emreozcan.tmdbapp.model.response.detail.credits.MovieCredits
import com.emreozcan.tmdbapp.ui.detail.adapter.CastAdapter
import com.emreozcan.tmdbapp.utils.Constants.API_KEY
import com.emreozcan.tmdbapp.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by @Emre Özcan on 22.06.2022
 */

@HiltViewModel
class DetailViewModel @Inject constructor(private val repository: DetailRepository) : ViewModel() {

    val movieDetail: MutableLiveData<MovieDetail?> = MutableLiveData()
    val movieCredits: MutableLiveData<MovieCredits?> = MutableLiveData()

    val onError: MutableLiveData<String?> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData(true)

    var adapter: CastAdapter? = null

    fun getMovieDetail(movieId: String) = viewModelScope.launch {
        isLoading.postValue(true)
        when (val request = repository.getMovieDetail(movieId, API_KEY)) {
            is NetworkResult.Success -> {
                movieDetail.postValue(request.data)
                isLoading.postValue(false)
            }
            is NetworkResult.Error -> {
                onError.postValue(request.message)
                isLoading.postValue(false)
            }
        }
    }

    fun getMovieCredits(movieId: String) = viewModelScope.launch {
        isLoading.postValue(true)
        when (val request = repository.getMovieCredits(movieId, API_KEY)) {
            is NetworkResult.Success -> {
                movieCredits.postValue(request.data)
                isLoading.postValue(false)
            }
            is NetworkResult.Error -> {
                onError.postValue(request.message)
                isLoading.postValue(false)
            }
        }
    }

}