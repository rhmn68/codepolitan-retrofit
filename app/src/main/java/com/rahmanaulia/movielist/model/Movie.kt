package com.rahmanaulia.movielist.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val image: Int? = null,
    val title: String? = null,
    val releaseDate: String? = null,
    val genre: String? = null,
    val rating: Float? = null
): Parcelable