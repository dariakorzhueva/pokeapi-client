package com.korzhueva.pokeapiclient.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.korzhueva.pokeapiclient.models.PokemonItem

class PokemonDetailViewModel(pokemon: PokemonItem, app: Application) : AndroidViewModel(app) {
    private val _selectedProperty = MutableLiveData<PokemonItem>()

    val selectedProperty: LiveData<PokemonItem>
        get() = _selectedProperty

    init {
        _selectedProperty.value = pokemon
    }
}
