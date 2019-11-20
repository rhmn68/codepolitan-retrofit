package com.rahmanaulia.movielist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rahmanaulia.movielist.model.Movie
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {

    companion object{
        const val EXTRA_MOVIE = "extra_movie"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        getDataIntent()
    }

    private fun getDataIntent() {
        val movie = intent.getParcelableExtra<Movie>(EXTRA_MOVIE)
        if (movie != null){
            movie.image?.let { ivMovieDetail.setImageResource(it) }
            tvTitleDetail.text = movie.title
            tvReleaseDateDetail.text = movie.releaseDate
            tvGenreDetail.text = movie.genre
            tvTotalRatingDetail.text = movie.rating.toString()
            movie.rating?.let { ratingBarMovieDetail.rating = it }
        }
    }
}
