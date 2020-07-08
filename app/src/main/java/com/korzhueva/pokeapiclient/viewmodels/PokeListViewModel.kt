package com.korzhueva.pokeapiclient.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.korzhueva.pokeapiclient.models.PokemonItem
import com.korzhueva.pokeapiclient.repository.PokemonRepository
import kotlinx.coroutines.launch

class PokeListViewModel : ViewModel() {

    private val pokemonRepository = PokemonRepository()

    private val _photoList = MutableLiveData<List<PokemonItem>>()
    val photoList: LiveData<List<PokemonItem>>
        get() = _photoList

    private val _navigateToSelectedPokemon = MutableLiveData<PokemonItem>()

    val navigateToSelectedPokemon: LiveData<PokemonItem>
        get() = _navigateToSelectedPokemon

    init {
        viewModelScope.launch{
            pokemonRepository.refreshData(0)

            _photoList.value = pokemonRepository.itemsConverted
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