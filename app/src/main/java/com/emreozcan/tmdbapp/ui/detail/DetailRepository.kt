package com.emreozcan.tmdbapp.ui.detail

import com.emreozcan.tmdbapp.base.BaseRepository
import com.emreozcan.tmdbapp.network.ApiFactory
import javax.inject.Inject

/**
 * Created by @Emre Özcan on 22.06.2022
 */
class DetailRepository @Inject constructor(
    private val apiFactory: ApiFactory
): BaseRepository(){

    suspend fun getMovieDetail(
        movieId: String,
        apiKey: String
    ) = invoke {
        apiFactory.getMovieDetail(movieId,apiKey)
    }

    suspend fun getMovieCredits(
        movieId: String,
        apiKey: String
    ) = invoke {
        apiFactory.getMovieCredits(movieId,apiKey)
    }
}