package com.korzhueva.pokeapiclient.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.korzhueva.pokeapiclient.models.PokemonItem
import com.korzhueva.pokeapiclient.repository.PokemonRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class PokeListViewModel : ViewModel() {

    private val pokemonRepository = PokemonRepository()

    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private val _photoList = MutableLiveData<List<PokemonItem>>()
    val photoList: LiveData<List<PokemonItem>>
        get() = _photoList

    private val _navigateToSelectedPokemon = MutableLiveData<PokemonItem>()

    // The external immutable LiveData for the navigation property
    val navigateToSelectedPokemon: LiveData<PokemonItem>
        get() = _navigateToSelectedPokemon

    init {
        coroutineScope.launch {
            pokemonRepository.refreshData(0)

            _photoList.value = pokemonRepository.itemsConverted
        }
    }

    fun displayPropertyDetails(pokemon: PokemonItem) {
        _navigateToSelectedPokemon.value = pokemon
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedPokemon.value = null
    }

    override fun onCleared() {
        super.onCleared()
    }
}