package com.emreozcan.tmdbapp.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.emreozcan.tmdbapp.databinding.CardItemLayoutBinding
import com.emreozcan.tmdbapp.model.response.home.Movie

/**
 * Created by @Emre Özcan on 22.06.2022
 */
class MovieAdapter(private val listener: ItemClickListener) :
    RecyclerView.Adapter<MovieAdapter.MViewHolder>() {

    private var movies = emptyList<Movie>()

    class MViewHolder(private val binding: CardItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: ItemClickListener, movie: Movie) {
            binding.onItemClickListener = listener
            binding.movie = movie
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): MViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = CardItemLayoutBinding.inflate(layoutInflater, parent, false)
                return MViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MViewHolder.from(parent)

    override fun onBindViewHolder(holder: MViewHolder, position: Int) =
        holder.bind(listener, movies[position])

    override fun getItemCount() = movies.size

    fun setList(newList: List<Movie>) {
        movies = newList
        notifyDataSetChanged()
    }

    fun deleteList() {
        movies = emptyList()
        notifyDataSetChanged()
    }
}

interface ItemClickListener {
    fun onItemClick(movieId: String)
}