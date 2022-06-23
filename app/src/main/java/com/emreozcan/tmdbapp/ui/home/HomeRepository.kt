package com.emreozcan.tmdbapp.ui.home

import com.emreozcan.tmdbapp.base.BaseRepository
import com.emreozcan.tmdbapp.network.ApiFactory
import javax.inject.Inject

/**
 * Created by @Emre Özcan on 22.06.2022
 */
class HomeRepository @Inject constructor(
    private val apiFactory: ApiFactory
): BaseRepository(){

    suspend fun getPopularMovies(
        apiKey: String
    ) = invoke {
        apiFactory.getPopularMovies(apiKey)
    }

    suspend fun getSearchMovie(
        apiKey: String,
        query: String
    ) = invoke {
        apiFactory.getSearchMovies(query,apiKey)
    }
}