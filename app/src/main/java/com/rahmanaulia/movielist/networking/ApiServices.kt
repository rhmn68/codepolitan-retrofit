package com.rahmanaulia.movielist.networking

import com.rahmanaulia.movielist.BuildConfig

object ApiServices {
    fun getMovieServices() : MovieApiServices{
        return RetrofitClient
            .getClient(BuildConfig.BASE_URL_MOVIE + BuildConfig.API_VERSION)
            .create(MovieApiServices::class.java)
    }
}