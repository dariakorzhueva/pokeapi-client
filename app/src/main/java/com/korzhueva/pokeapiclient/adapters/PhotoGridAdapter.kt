package com.korzhueva.pokeapiclient.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.korzhueva.pokeapiclient.databinding.GridViewItemBinding
import com.korzhueva.pokeapiclient.models.PokemonItem

class PhotoGridAdapter(val onClickListener: OnClickListener) :
    ListAdapter<PokemonItem, PhotoGridAdapter.PhotoViewHolder>(DiffCallback) {
    class PhotoViewHolder(private var binding: GridViewItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(pokemonItem: PokemonItem) {
            binding.item = pokemonItem
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<PokemonItem>() {
        override fun areItemsTheSame(oldItem: PokemonItem, newItem: PokemonItem): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: PokemonItem, newItem: PokemonItem): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PhotoViewHolder {
        return PhotoViewHolder(GridViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val photoItem = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(photoItem)
        }
        holder.bind(photoItem)
    }

    class OnClickListener(val clickListener: (photoItem: PokemonItem) -> Unit) {
        fun onClick(photoItem: PokemonItem) = clickListener(photoItem)
    }
}