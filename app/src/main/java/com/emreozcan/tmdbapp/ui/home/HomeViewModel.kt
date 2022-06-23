package com.emreozcan.tmdbapp.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.emreozcan.tmdbapp.model.response.home.MoviesModel
import com.emreozcan.tmdbapp.ui.home.adapter.MovieAdapter
import com.emreozcan.tmdbapp.utils.Constants.API_KEY
import com.emreozcan.tmdbapp.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by @Emre Özcan on 22.06.2022
 */
@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: HomeRepository): ViewModel() {

    val moviesList: MutableLiveData<MoviesModel?> = MutableLiveData()

    val onError: MutableLiveData<String?> = MutableLiveData()
    val isLoading: MutableLiveData<Boolean> = MutableLiveData(true)

    var movieAdapter: MovieAdapter ?= null

    init {
        getPopularMovies()
    }

     fun getPopularMovies() = viewModelScope.launch {
        isLoading.value = true
        when(val request = repository.getPopularMovies(API_KEY)){
            is NetworkResult.Success -> {
                moviesList.postValue(request.data)
                isLoading.value = false
            }
            is NetworkResult.Error -> {
                if (request.networkError){
                    onError.postValue("There is any internet connection.")
                }else {
                    onError.postValue(request.message)

                }
                isLoading.value = false
            }
        }
    }

    fun getSearchMovies(search: String) = viewModelScope.launch {
        isLoading.value = true
        when (val request = repository.getSearchMovie(search, API_KEY)) {
            is NetworkResult.Success -> {
                moviesList.postValue(request.data)
                isLoading.value = false
            }
            is NetworkResult.Error -> {
                onError.postValue(request.message)
                isLoading.value = false
            }
        }
    }
}