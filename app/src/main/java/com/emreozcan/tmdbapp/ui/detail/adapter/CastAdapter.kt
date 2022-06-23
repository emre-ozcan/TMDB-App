package com.emreozcan.tmdbapp.ui.detail.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.emreozcan.tmdbapp.databinding.LayoutCastItemBinding
import com.emreozcan.tmdbapp.model.response.detail.credits.Cast

/**
 * Created by @Emre Özcan on 22.06.2022
 */
class CastAdapter(private val listener: ItemCastClickListener) :
    RecyclerView.Adapter<CastAdapter.MViewHolder>() {

    private var casts = emptyList<Cast>()

    class MViewHolder(private val binding: LayoutCastItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(listener: ItemCastClickListener, cast: Cast) {
            binding.onClickListener = listener
            binding.cast = cast
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): MViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = LayoutCastItemBinding.inflate(layoutInflater, parent, false)
                return MViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MViewHolder.from(parent)

    override fun onBindViewHolder(holder: MViewHolder, position: Int) =
        holder.bind(listener, casts[position])

    override fun getItemCount() = casts.size

    fun setList(newList: List<Cast>) {
        casts = newList
        notifyDataSetChanged()
    }
}

interface ItemCastClickListener {
    fun onItemClick(cast: Cast)
}