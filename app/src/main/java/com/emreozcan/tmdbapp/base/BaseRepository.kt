package com.emreozcan.tmdbapp.base

import com.emreozcan.tmdbapp.R
import com.emreozcan.tmdbapp.di.TMDBApp.Companion.getAppContext
import com.emreozcan.tmdbapp.model.error.ErrorResponse
import com.emreozcan.tmdbapp.utils.NetworkResult
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.lang.Exception

/**
 * Created by @Emre Özcan on 22.06.2022
 */
abstract class BaseRepository {

    suspend fun <T> invoke(
        apiRequest: suspend () -> T): NetworkResult<T> {
        return withContext(Dispatchers.IO){
            try {
                NetworkResult.Success(apiRequest.invoke())
            }catch (throwable: Throwable){
                when(throwable){
                    is HttpException -> {
                        NetworkResult.Error(false, errorBodyParser(throwable.response()?.errorBody()?.string()))
                    }
                    else ->  NetworkResult.Error(true,throwable.localizedMessage)
                }
            }
        }
    }
}

private fun errorBodyParser(error: String?): String{
    error?.let {
        return try {
            val errorResponse = Gson().fromJson(error, ErrorResponse::class.java)
            val errorMessage = errorResponse.statusMessage
            errorMessage ?: getAppContext().getString(R.string.unknown_error)
        }catch (e: Exception){
            getAppContext().getString(R.string.unknown_error)
        }
    }
    return getAppContext().getString(R.string.unknown_error)
}