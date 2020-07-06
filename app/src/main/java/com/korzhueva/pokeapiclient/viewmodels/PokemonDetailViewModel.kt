package com.korzhueva.pokeapiclient.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.korzhueva.pokeapiclient.models.PokemonItem

class PokemonDetailViewModel(pokemon: PokemonItem, app: Application) : AndroidViewModel(app) {
    private val _selectedPokemon = MutableLiveData<PokemonItem>()

    val selectedPokemon: LiveData<PokemonItem>
        get() = _selectedPokemon

    init {
        _selectedPokemon.value = pokemon
    }
}
