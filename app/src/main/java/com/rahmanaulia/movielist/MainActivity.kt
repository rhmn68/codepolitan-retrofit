package com.rahmanaulia.movielist

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.rahmanaulia.movielist.adapter.MovieAdapter
import com.rahmanaulia.movielist.data.Data
import com.rahmanaulia.movielist.model.Response
import com.rahmanaulia.movielist.model.ResultsItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getDataApi()
    }

    private fun getDataApi() {
        Data(this).getDataMovieApi(object : Data.CallbackApi{
            override fun onDataLoad(response: Response) {
                response.results?.let { initAdapter(it) }
            }

            override fun onDataEmpty() {
                Toast.makeText(this@MainActivity, "Kosong",
                    Toast.LENGTH_SHORT).show()
            }

            override fun onDataError(msg: String?) {
                Toast.makeText(this@MainActivity, msg,
                    Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun initAdapter(results: List<ResultsItem>?) {
        Data(this).getDataMovie()
        val adapter = MovieAdapter(results as ArrayList<ResultsItem>){ movie->
            Toast.makeText(this@MainActivity, movie.title,
                Toast.LENGTH_SHORT).show()
        }
        adapter.notifyDataSetChanged()

        rvMain.layoutManager = LinearLayoutManager(this)
        rvMain.adapter = adapter
    }
}
