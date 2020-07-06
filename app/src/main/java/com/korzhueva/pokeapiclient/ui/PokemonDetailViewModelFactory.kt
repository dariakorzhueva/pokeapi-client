package com.korzhueva.pokeapiclient.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.korzhueva.pokeapiclient.models.PokemonItem
import com.korzhueva.pokeapiclient.viewmodels.PokemonDetailViewModel

class PokemonDetailViewModelFactory(
    private val pokemon: PokemonItem,
    private val application: Application
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PokemonDetailViewModel::class.java)) {
            return PokemonDetailViewModel(pokemon, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}