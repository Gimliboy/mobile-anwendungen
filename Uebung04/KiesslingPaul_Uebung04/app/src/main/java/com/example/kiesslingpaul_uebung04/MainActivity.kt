package com.example.kiesslingpaul_uebung04

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.autofill.TextValueSanitizer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_movie.view.*
import org.apache.commons.io.IOUtils
import java.io.InputStream
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // read the file 'movies.json'
        val iStream = resources.openRawResource(R.raw.movies)
        val jsonText = IOUtils.toString(iStream, Charset.defaultCharset())

        // Parse movie Bib with Gson
        val gson = Gson()
        val movieBib = gson.fromJson<MovieBib>(jsonText, MovieBib::class.java)
        // get the movies in the bib
        val parsedMovies = movieBib.movies

        // init Recyclerview with list of movies
        val adapter_value = MyAdapter(parsedMovies)
        moviesRecyclerView.adapter = adapter_value
        moviesRecyclerView.layoutManager = LinearLayoutManager(this)

        // set the Header TextView's
        findViewById<TextView>(R.id.userGreetingTextView).text = "Willkomen zurück, " + movieBib.user
    findViewById<TextView>(R.id.lastLoginTextView).text = "Letzter Login: "+movieBib.LastLogin
    }
}

data class MovieBib(val user: String, val LastLogin: String, val movies: List<Movie>)
data class Movie(val title: String, val year: Int, val duration: Int, val imdbScore: Float, val synchronized: Boolean, val cast: List<String>)

class MyAdapter(private val data_list: List<Movie>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount() = data_list.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = data_list[position]
        holder.myMovieTitle.text = currentItem.title
        holder.myMovieRating.text = currentItem.imdbScore.toString()
        holder.myMovieDuration.text = currentItem.duration.toString()
        holder.myMovieCast.text = currentItem.cast.joinToString()
        if (currentItem.synchronized)
        {
            holder.myMovieSynchronized.setImageResource(R.drawable.ic_baseline_mic_24)
        }
        else
        {
            holder.myMovieSynchronized.setImageResource(R.drawable.ic_baseline_mic_off_24)
        }
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val myMovieTitle: TextView = itemView.item_movie_title
        val myMovieRating: TextView = itemView.item_movie_rating
        val myMovieDuration: TextView = itemView.item_movie_year_duration
        val myMovieCast: TextView = itemView.item_move_cast
        val myMovieSynchronized: ImageView = itemView.item_movie_synchronized

    }
}