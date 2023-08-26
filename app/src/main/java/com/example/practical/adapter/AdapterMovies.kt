package com.example.practical.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.practical.databinding.ItemMovieBinding
import com.example.practical.models.Search
import com.example.practical.views.MovieDetailActivity

class AdapterMovies(
    private val moviesList: List<Search>
) : RecyclerView.Adapter<MoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val itemView = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(moviesList[position])

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, MovieDetailActivity::class.java)
            intent.putExtra("movieId", moviesList[position].imdbID)
            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

}

class MoviesViewHolder(
    private val binding: ItemMovieBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(movies: Search) {
        binding.movie = movies
    }
}
