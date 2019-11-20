package com.rahmanaulia.movielist.data

import android.content.Context
import android.util.Log
import com.rahmanaulia.movielist.BuildConfig
import com.rahmanaulia.movielist.R
import com.rahmanaulia.movielist.model.Movie
import com.rahmanaulia.movielist.model.Response
import com.rahmanaulia.movielist.model.ResultsItem
import com.rahmanaulia.movielist.networking.ApiServices
import retrofit2.Call
import retrofit2.Callback

class Data(context: Context){

    private val dataTitle = context.resources.getStringArray(R.array.data_title)
    private val dataReleaseDate = context.resources.getStringArray(R.array.data_release_date)
    private val dataGenre = context.resources.getStringArray(R.array.data_genre)
    private val dataRating = context.resources.getStringArray(R.array.data_rating)
    private val dataImage  = context.resources.obtainTypedArray(R.array.data_image)

    fun getDataMovie() : ArrayList<Movie>{
        val movieList = ArrayList<Movie>()
        for (i in dataTitle.indices){
            movieList.add(
                Movie(
                dataImage.getResourceId(i, 0),
                    dataTitle[i],
                    dataReleaseDate[i],
                    dataGenre[i],
                    dataRating[i].toFloat()
            ))
        }
        return movieList
    }

    fun getDataMovieApi(callback: CallbackApi){
        ApiServices.getMovieServices()
            .getMovie(BuildConfig.TOKEN_MOVIE_DB)
            .enqueue(object: Callback<Response>{
                override fun onFailure(call: Call<Response>, t: Throwable) {
                    callback.onDataError(t.message)
                }

                override fun onResponse(
                    call: Call<Response>,
                    response: retrofit2.Response<Response>
                ) {
                    if (response.isSuccessful){
                        val responseMovie = response.body()
                        if (responseMovie != null){
                            callback.onDataLoad(responseMovie)
                        }else{
                            callback.onDataEmpty()
                        }
                    }
                }
            } )
    }

    interface CallbackApi{
        fun onDataLoad(response: Response)
        fun onDataEmpty()
        fun onDataError(msg: String?)
    }
}