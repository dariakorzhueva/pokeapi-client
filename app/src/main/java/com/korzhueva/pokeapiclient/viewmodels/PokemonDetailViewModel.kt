package com.korzhueva.pokeapiclient.viewmodels

import android.app.Application
import androidx.lifecycle.*
import com.korzhueva.pokeapiclient.models.PokemonItem

class PokemonDetailViewModel(pokemon: PokemonItem, app: Application) : AndroidViewModel(app) {
    private val _selectedPokemon = MutableLiveData<PokemonItem>()
    var weight = "Weight: "
    var height = "Height: "
    var type = "Type: "
    var hp = "HP: "
    var attack = "Attack: "
    var defense = "Defense: "

    val selectedPokemon: LiveData<PokemonItem>
        get() = _selectedPokemon

    init {
        _selectedPokemon.value = pokemon

        weight += pokemon.weight
        height += pokemon.height
        type += pokemon.type
        hp += pokemon.hp
        attack += pokemon.attack
        defense += pokemon.defense
    }
}
