package com.korzhueva.pokeapiclient.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.korzhueva.pokeapiclient.adapters.PhotoGridAdapter
import com.korzhueva.pokeapiclient.models.PokemonItem
import com.korzhueva.pokeapiclient.repository.PokemonRepository
import kotlinx.coroutines.launch

class PokeListViewModel : ViewModel() {
    private val pokemonRepository = PokemonRepository()

    private val _photoList = MutableLiveData<MutableList<PokemonItem>>()
    val photoList: LiveData<MutableList<PokemonItem>>
        get() = _photoList

    private val _navigateToSelectedPokemon = MutableLiveData<PokemonItem>()

    val navigateToSelectedPokemon: LiveData<PokemonItem>
        get() = _navigateToSelectedPokemon

    private var offset = 0

    private var _isLoading = false
    val isLoading: Boolean
        get() = _isLoading

    init {
        viewModelScope.launch {
            pokemonRepository.refreshData(offset)

            _photoList.value = pokemonRepository.itemsConverted.toMutableList()
        }
    }

    fun loadMore(adapter: PhotoGridAdapter, total: Int) {
        offset += 30

        Log.d("loadMorePoke", "${_photoList.value!!.size}")

        viewModelScope.launch {
            _isLoading = true

            pokemonRepository.refreshData(offset)

            _photoList.value!!.addAll(pokemonRepository.itemsConverted)

            adapter.submitList(_photoList.value)
            adapter.notifyItemChanged(
                total,
                (photoList.value!!.size) - 1
            )

            _isLoading = false
        }
    }

    fun displayPokemonDetails(pokemon: PokemonItem) {
        _navigateToSelectedPokemon.value = pokemon
    }

    fun displayPokemonDetailsComplete() {
        _navigateToSelectedPokemon.value = null
    }

    override fun onCleared() {
        super.onCleared()
    }
}