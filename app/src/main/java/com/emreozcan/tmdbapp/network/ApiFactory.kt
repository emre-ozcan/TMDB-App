package com.emreozcan.tmdbapp.network

import com.emreozcan.tmdbapp.model.response.detail.MovieDetail
import com.emreozcan.tmdbapp.model.response.detail.credits.MovieCredits
import com.emreozcan.tmdbapp.model.response.home.MoviesModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by @Emre Özcan on 22.06.2022
 */
interface ApiFactory {

    companion object{
        const val POPULAR_MOVIES = "3/movie/popular"
        const val SEARCH_MOVIES = "3/search/movie"
        const val MOVIE_DETAIL = "3/movie/{movieId}"
        const val CREDITS = "3/movie/{movieId}/credits"
    }

    @GET(POPULAR_MOVIES)
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String
    ): MoviesModel

    @GET(SEARCH_MOVIES)
    suspend fun getSearchMovies(
        @Query("api_key") apiKey: String,
        @Query("query") query: String
    ): MoviesModel

    @GET(MOVIE_DETAIL)
    suspend fun getMovieDetail(
        @Path("movieId") movieId: String,
        @Query("api_key") apiKey: String
    ): MovieDetail

    @GET(CREDITS)
    suspend fun getMovieCredits(
        @Path("movieId") movieId: String,
        @Query("api_key") apiKey: String
    ): MovieCredits

}