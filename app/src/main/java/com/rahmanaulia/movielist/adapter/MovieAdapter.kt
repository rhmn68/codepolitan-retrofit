package com.rahmanaulia.movielist.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rahmanaulia.movielist.BuildConfig
import com.rahmanaulia.movielist.R
import com.rahmanaulia.movielist.model.ResultsItem
import kotlinx.android.synthetic.main.item_movie_list.view.*

class MovieAdapter(
    private val dataMovie: ArrayList<ResultsItem>,
    private val listener: (ResultsItem) -> Unit)
    : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_movie_list, parent, false))

    override fun getItemCount(): Int = dataMovie.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(dataMovie[position], listener)
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        fun bindItem(
            movie: ResultsItem,
            listener: (ResultsItem) -> Unit
        ){
            itemView.tvTitle.text = movie.title
            itemView.tvReleaseDate.text = movie.releaseDate
            itemView.tvGenre.text = "Comedy"
            val rating = movie.voteAverage?.div(2)
            itemView.tvTotalRating.text = rating.toString()
            itemView.ratingBarMovie.rating = rating!!.toFloat()

            val urlImage = BuildConfig.URL_IMAGE_MOVIE_DB + movie.posterPath
            Glide.with(itemView).load(urlImage).into(itemView.ivMovie)

            itemView.setOnClickListener {
                listener(movie)
            }
        }
    }
}