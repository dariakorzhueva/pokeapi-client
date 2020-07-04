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

    init {
        coroutineScope.launch {
            pokemonRepository.refreshData(0)

            _photoList.value = pokemonRepository.itemsConverted
        }
    }

    fun getPokemonsInfo() {
        coroutineScope.launch {
            if (_photoList.value != null) {
                    pokemonRepository.refreshPokemons()
            }
        }
    }


    override fun onCleared() {
        super.onCleared()
    }
}